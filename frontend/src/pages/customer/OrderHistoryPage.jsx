import { useState, useEffect } from 'react';
import api from '../../api';
import CustomerNav from '../../components/CustomerNav';

const statusLabel = { PENDING: '대기중', PREPARING: '준비중', COMPLETED: '완료' };
const statusClass = { PENDING: 'badge-pending', PREPARING: 'badge-preparing', COMPLETED: 'badge-completed' };

export default function OrderHistoryPage() {
  const [orders, setOrders] = useState([]);
  const tableId = localStorage.getItem('tableId');

  useEffect(() => {
    if (tableId) {
      api.get('/order/list', { params: { tableId } }).then(r => setOrders(r.data));
    }
  }, [tableId]);

  return (
    <div className="container" style={{ paddingBottom: 80 }}>
      <h1 className="page-title">주문 내역</h1>
      {orders.length === 0 ? (
        <div className="empty-state">
          <div className="icon">📋</div>
          <p>주문 내역이 없습니다</p>
        </div>
      ) : (
        orders.map(order => (
          <div key={order.id} className="card" data-testid={`order-card-${order.id}`}>
            <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: 12 }}>
              <span style={{ fontWeight: 700 }}>주문 #{order.id}</span>
              <span className={`badge ${statusClass[order.status]}`}>{statusLabel[order.status]}</span>
            </div>
            {order.items?.map(item => (
              <div key={item.id} style={{ display: 'flex', justifyContent: 'space-between', padding: '4px 0', fontSize: 14 }}>
                <span>{item.menuName} × {item.quantity}</span>
                <span>{(item.price * item.quantity).toLocaleString()}원</span>
              </div>
            ))}
            <div style={{ borderTop: '1px solid #f0f0f0', marginTop: 8, paddingTop: 8, textAlign: 'right', fontWeight: 700 }}>
              {order.totalAmount?.toLocaleString()}원
            </div>
          </div>
        ))
      )}
      <CustomerNav />
    </div>
  );
}
