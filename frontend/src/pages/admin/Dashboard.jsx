import { useState, useEffect, useRef } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../api';

const statusLabel = { PENDING: '대기중', PREPARING: '준비중', COMPLETED: '완료' };
const statusClass = { PENDING: 'badge-pending', PREPARING: 'badge-preparing', COMPLETED: 'badge-completed' };
const nextStatus = { PENDING: 'PREPARING', PREPARING: 'COMPLETED' };

export default function Dashboard() {
  const [tables, setTables] = useState([]);
  const [tableOrders, setTableOrders] = useState({});
  const [flashTable, setFlashTable] = useState(null);
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const eventSourceRef = useRef(null);

  const loadData = async () => {
    const tablesRes = await api.get('/table/list');
    setTables(tablesRes.data);
    const ordersMap = {};
    for (const t of tablesRes.data) {
      if (t.active) {
        const ordersRes = await api.get('/order/list', { params: { tableId: t.id } });
        ordersMap[t.id] = ordersRes.data;
      }
    }
    setTableOrders(ordersMap);
  };

  useEffect(() => {
    loadData();
    const token = localStorage.getItem('token');
    const es = new EventSource(`/api/sse/subscribe?token=${token}`);
    es.addEventListener('NEW_ORDER', () => { loadData(); });
    es.addEventListener('ORDER_STATUS_CHANGED', () => loadData());
    es.addEventListener('ORDER_DELETED', () => loadData());
    es.onerror = () => { es.close(); setTimeout(() => loadData(), 3000); };
    eventSourceRef.current = es;
    return () => es.close();
  }, []);

  const changeStatus = async (orderId, current) => {
    const next = nextStatus[current];
    if (!next) return;
    await api.put(`/order/update-status/${orderId}`, { status: next });
    loadData();
  };

  const deleteOrder = async (orderId) => {
    if (!confirm('이 주문을 삭제하시겠습니까?')) return;
    await api.delete(`/order/delete/${orderId}`);
    loadData();
  };

  return (
    <div className="admin-container">
      <h1 className="page-title">📊 주문 대시보드</h1>
      <div className="admin-nav">
        <a className={pathname === '/admin/dashboard' ? 'active' : ''} onClick={() => navigate('/admin/dashboard')}>대시보드</a>
        <a className={pathname === '/admin/tables' ? 'active' : ''} onClick={() => navigate('/admin/tables')}>테이블 관리</a>
        <a onClick={() => { localStorage.clear(); navigate('/admin/login'); }} style={{ marginLeft: 'auto', color: '#ff3b30' }}>로그아웃</a>
      </div>
      {tables.filter(t => t.active).length === 0 ? (
        <div className="empty-state">
          <div className="icon">📊</div>
          <p>활성 테이블이 없습니다</p>
        </div>
      ) : (
        <div className="order-grid">
          {tables.filter(t => t.active).map(table => {
            const orders = tableOrders[table.id] || [];
            const hasOrders = orders.length > 0;
            return (
              <div key={table.id} className={`table-card ${hasOrders ? 'has-orders' : 'active'}`}
                   data-testid={`dashboard-table-${table.id}`}>
                <div className="table-card-header">
                  <span className="table-number">테이블 {table.tableNumber}</span>
                  <span style={{ fontSize: 14, color: '#86868b' }}>{orders.length}건</span>
                </div>
                {orders.map(order => (
                  <div key={order.id} style={{ borderTop: '1px solid #f0f0f0', padding: '10px 0' }}>
                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 6 }}>
                      <span style={{ fontWeight: 600, fontSize: 14 }}>#{order.id}</span>
                      <span className={`badge ${statusClass[order.status]}`}>{statusLabel[order.status]}</span>
                    </div>
                    {order.items?.map(item => (
                      <div key={item.id} style={{ fontSize: 13, color: '#6e6e73', padding: '2px 0' }}>
                        {item.menuName} × {item.quantity}
                      </div>
                    ))}
                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: 8 }}>
                      <span style={{ fontWeight: 700 }}>{order.totalAmount?.toLocaleString()}원</span>
                      <div style={{ display: 'flex', gap: 6 }}>
                        {nextStatus[order.status] && (
                          <button className="btn-success" style={{ fontSize: 12, padding: '6px 12px' }}
                                  onClick={() => changeStatus(order.id, order.status)}
                                  data-testid={`status-btn-${order.id}`}>
                            {statusLabel[nextStatus[order.status]]}으로
                          </button>
                        )}
                        <button className="btn-danger" style={{ fontSize: 12, padding: '6px 12px' }}
                                onClick={() => deleteOrder(order.id)}
                                data-testid={`delete-order-${order.id}`}>
                          삭제
                        </button>
                      </div>
                    </div>
                  </div>
                ))}
                {orders.length === 0 && <p style={{ color: '#86868b', fontSize: 14 }}>주문 없음</p>}
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
}
