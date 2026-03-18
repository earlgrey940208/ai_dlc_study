import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../api';

export default function CustomerLogin() {
  const [tableNumber, setTableNumber] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const res = await api.post('/auth/table-login', {
        tableNumber: Number(tableNumber), password
      });
      localStorage.setItem('token', res.data.token);
      localStorage.setItem('tableId', res.data.tableId);
      localStorage.setItem('role', 'TABLE');
      navigate('/customer/menu');
    } catch {
      setError('인증 실패. 테이블 번호와 비밀번호를 확인해주세요.');
    }
  };

  return (
    <div className="login-page">
      <h1>🍽️ 테이블오더</h1>
      <p>테이블 번호와 비밀번호를 입력해주세요</p>
      <form className="login-form" onSubmit={handleLogin}>
        <input type="number" placeholder="테이블 번호" value={tableNumber}
               onChange={e => setTableNumber(e.target.value)} min="1" required
               data-testid="table-number-input" />
        <input type="password" placeholder="비밀번호" value={password}
               onChange={e => setPassword(e.target.value)} required
               data-testid="table-password-input" />
        {error && <p style={{ color: '#ff3b30', fontSize: 14 }}>{error}</p>}
        <button className="btn-primary" type="submit" data-testid="table-login-btn">
          시작하기
        </button>
      </form>
      <a onClick={() => navigate('/admin/login')}
         style={{ marginTop: 24, color: '#86868b', fontSize: 14, cursor: 'pointer' }}>
        관리자 로그인 →
      </a>
    </div>
  );
}
