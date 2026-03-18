import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../api';
import CustomerNav from '../../components/CustomerNav';

export default function CartPage() {
  const [cart, setCart] = useState(JSON.parse(localStorage.getItem('cart') || '[]'));
  const navigate = useNavigate();
  const tableId = localStorage.getItem('tableId');

  const updateCart = (updated) => {
    setCart(updated);
    localStorage.setItem('cart', JSON.stringify(updated));
  };

  const changeQty = (menuId, delta) => {
    const updated = cart.map(i =>
      i.menuId === menuId ? { ...i, quantity: Math.max(1, i.quantity + delta) } : i
    );
    updateCart(updated);
  };

  const removeItem = (menuId) => {
    updateCart(cart.filter(i => i.menuId !== menuId));
  };

  const total = cart.reduce((sum, i) => sum + i.price * i.quantity, 0);

  const placeOrder = async () => {
    if (cart.length === 0) return;
    try {
      const res = await api.post('/order/create', {
        tableId: Number(tableId),
        items: cart.map(i => ({ menuId: i.menuId, quantity: i.quantity }))
      });
      localStorage.setItem('cart', '[]');
      alert(`주문 완료! 주문번호: ${res.data.id}`);
      setTimeout(() => navigate('/customer/menu'), 2000);
    } catch (e) {
      alert('주문 실패: ' + (e.response?.data?.error || '오류 발생'));
    }
  };

  return (
    <div className="container" style={{ paddingBottom: 140 }}>
      <h1 className="page-title">장바구니</h1>
      {cart.length === 0 ? (
        <div className="empty-state">
          <div className="icon">🛒</div>
          <p>장바구니가 비어있습니다</p>
        </div>
      ) : (
        <div className="card">
          {cart.map(item => (
            <div key={item.menuId} className="cart-item" data-testid={`cart-item-${item.menuId}`}>
              <div className="cart-item-info">
                <div className="cart-item-name">{item.menuName}</div>
                <div className="cart-item-price">{(item.price * item.quantity).toLocaleString()}원</div>
              </div>
              <div className="qty-control">
                <button onClick={() => changeQty(item.menuId, -1)} data-testid={`qty-minus-${item.menuId}`}>−</button>
                <span>{item.quantity}</span>
                <button onClick={() => changeQty(item.menuId, 1)} data-testid={`qty-plus-${item.menuId}`}>+</button>
                <button onClick={() => removeItem(item.menuId)} style={{ background: '#ff3b30', color: 'white' }}
                        data-testid={`remove-item-${item.menuId}`}>✕</button>
              </div>
            </div>
          ))}
        </div>
      )}
      {cart.length > 0 && (
        <div className="total-bar">
          <div className="total-amount">{total.toLocaleString()}원</div>
          <button className="btn-primary" onClick={placeOrder} data-testid="place-order-btn">
            주문하기
          </button>
        </div>
      )}
      <CustomerNav />
    </div>
  );
}
