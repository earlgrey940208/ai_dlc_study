import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../api';

export default function AdminLogin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const res = await api.post('/auth/login', { username, password });
      localStorage.setItem('token', res.data.token);
      localStorage.setItem('role', 'ADMIN');
      navigate('/admin/dashboard');
    } catch {
      setError('로그인 실패. 아이디와 비밀번호를 확인해주세요.');
    }
  };

  return (
    <div className="login-page">
      <h1>🔐 관리자</h1>
      <p>매장 관리 시스템에 로그인하세요</p>
      <form className="login-form" onSubmit={handleLogin}>
        <input type="text" placeholder="아이디" value={username}
               onChange={e => setUsername(e.target.value)} required
               data-testid="admin-username-input" />
        <input type="password" placeholder="비밀번호" value={password}
               onChange={e => setPassword(e.target.value)} required
               data-testid="admin-password-input" />
        {error && <p style={{ color: '#ff3b30', fontSize: 14 }}>{error}</p>}
        <button className="btn-primary" type="submit" data-testid="admin-login-btn">
          로그인
        </button>
      </form>
      <a onClick={() => navigate('/customer/login')}
         style={{ marginTop: 24, color: '#86868b', fontSize: 14, cursor: 'pointer' }}>
        ← 고객 화면으로
      </a>
    </div>
  );
}
