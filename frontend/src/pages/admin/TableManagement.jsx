import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../api';

export default function TableManagement() {
  const [tables, setTables] = useState([]);
  const [showSetup, setShowSetup] = useState(false);
  const [showHistory, setShowHistory] = useState(null);
  const [history, setHistory] = useState([]);
  const [newTable, setNewTable] = useState({ tableNumber: '', password: '' });
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const loadTables = () => api.get('/table/list').then(r => setTables(r.data));

  useEffect(() => { loadTables(); }, []);

  const setupTable = async (e) => {
    e.preventDefault();
    try {
      await api.post('/table/setup', {
        tableNumber: Number(newTable.tableNumber), password: newTable.password
      });
      setShowSetup(false);
      setNewTable({ tableNumber: '', password: '' });
      loadTables();
    } catch (err) {
      alert(err.response?.data?.error || '테이블 설정 실패');
    }
  };

  const completeTable = async (tableId) => {
    if (!confirm('이용 완료 처리하시겠습니까? 주문이 이력으로 이동됩니다.')) return;
    await api.post(`/table/complete/${tableId}`);
    loadTables();
  };

  const viewHistory = async (tableId) => {
    const res = await api.get(`/table/history/${tableId}`);
    setHistory(res.data);
    setShowHistory(tableId);
  };

  return (
    <div className="admin-container">
      <h1 className="page-title">🪑 테이블 관리</h1>
      <div className="admin-nav">
        <a className={pathname === '/admin/dashboard' ? 'active' : ''} onClick={() => navigate('/admin/dashboard')}>대시보드</a>
        <a className={pathname === '/admin/tables' ? 'active' : ''} onClick={() => navigate('/admin/tables')}>테이블 관리</a>
        <a onClick={() => { localStorage.clear(); navigate('/admin/login'); }} style={{ marginLeft: 'auto', color: '#ff3b30' }}>로그아웃</a>
      </div>

      <button className="btn-primary" style={{ width: 'auto', marginBottom: 20 }}
              onClick={() => setShowSetup(true)} data-testid="add-table-btn">
        + 테이블 추가
      </button>

      <div className="order-grid">
        {tables.map(table => (
          <div key={table.id} className={`table-card ${table.active ? 'active' : ''}`}
               data-testid={`table-card-${table.id}`}>
            <div className="table-card-header">
              <span className="table-number">테이블 {table.tableNumber}</span>
              <span className={`badge ${table.active ? 'badge-preparing' : 'badge-completed'}`}>
                {table.active ? '사용중' : '비활성'}
              </span>
            </div>
            <div className="table-actions">
              {table.active && (
                <button className="btn-danger" onClick={() => completeTable(table.id)}
                        data-testid={`complete-table-${table.id}`}>
                  이용 완료
                </button>
              )}
              <button className="btn-secondary" onClick={() => viewHistory(table.id)}
                      data-testid={`history-table-${table.id}`}>
                과거 내역
              </button>
            </div>
          </div>
        ))}
      </div>

      {showSetup && (
        <div className="modal-overlay" onClick={() => setShowSetup(false)}>
          <div className="modal" onClick={e => e.stopPropagation()}>
            <h3>테이블 추가</h3>
            <form onSubmit={setupTable} style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
              <input type="number" placeholder="테이블 번호" min="1" required
                     value={newTable.tableNumber}
                     onChange={e => setNewTable({ ...newTable, tableNumber: e.target.value })}
                     data-testid="setup-table-number" />
              <input type="password" placeholder="비밀번호" required
                     value={newTable.password}
                     onChange={e => setNewTable({ ...newTable, password: e.target.value })}
                     data-testid="setup-table-password" />
              <div className="modal-actions">
                <button type="button" className="btn-secondary" onClick={() => setShowSetup(false)}>취소</button>
                <button type="submit" className="btn-primary" data-testid="setup-table-submit">추가</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {showHistory !== null && (
        <div className="modal-overlay" onClick={() => setShowHistory(null)}>
          <div className="modal" onClick={e => e.stopPropagation()} style={{ maxHeight: '80vh', overflow: 'auto' }}>
            <h3>과거 주문 내역</h3>
            {history.length === 0 ? (
              <p style={{ color: '#86868b' }}>내역이 없습니다</p>
            ) : (
              history.map(h => (
                <div key={h.id} className="card" style={{ marginBottom: 8 }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: 14 }}>
                    <span style={{ fontWeight: 600 }}>{h.totalAmount?.toLocaleString()}원</span>
                    <span style={{ color: '#86868b' }}>{new Date(h.completedAt).toLocaleString('ko-KR')}</span>
                  </div>
                </div>
              ))
            )}
            <button className="btn-secondary" style={{ width: '100%', marginTop: 12 }}
                    onClick={() => setShowHistory(null)}>닫기</button>
          </div>
        </div>
      )}
    </div>
  );
}
