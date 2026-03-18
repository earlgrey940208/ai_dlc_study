import { useNavigate, useLocation } from 'react-router-dom';

export default function CustomerNav() {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const items = [
    { path: '/customer/menu', icon: '🍽️', label: '메뉴' },
    { path: '/customer/cart', icon: '🛒', label: '장바구니' },
    { path: '/customer/orders', icon: '📋', label: '주문내역' },
  ];
  return (
    <nav className="nav-bottom">
      {items.map(i => (
        <a key={i.path} className={pathname === i.path ? 'active' : ''}
           onClick={() => navigate(i.path)}>
          <span className="nav-icon">{i.icon}</span>
          {i.label}
        </a>
      ))}
    </nav>
  );
}
