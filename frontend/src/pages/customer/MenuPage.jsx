import { useState, useEffect } from 'react';
import api from '../../api';
import CustomerNav from '../../components/CustomerNav';

export default function MenuPage() {
  const [categories, setCategories] = useState([]);
  const [menus, setMenus] = useState([]);
  const [activeCategory, setActiveCategory] = useState(null);

  useEffect(() => {
    api.get('/menu/categories').then(r => {
      setCategories(r.data);
      if (r.data.length > 0) setActiveCategory(r.data[0].id);
    });
  }, []);

  useEffect(() => {
    api.get('/menu/list', { params: { categoryId: activeCategory } })
      .then(r => setMenus(r.data));
  }, [activeCategory]);

  const addToCart = (menu) => {
    const cart = JSON.parse(localStorage.getItem('cart') || '[]');
    const existing = cart.find(i => i.menuId === menu.id);
    if (existing) {
      existing.quantity += 1;
    } else {
      cart.push({ menuId: menu.id, menuName: menu.name, price: menu.price, imageUrl: menu.imageUrl, quantity: 1 });
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    alert(`${menu.name} 추가됨!`);
  };

  return (
    <div className="container">
      <h1 className="page-title">메뉴</h1>
      <div className="tab-bar">
        {categories.map(c => (
          <button key={c.id} className={activeCategory === c.id ? 'active' : ''}
                  onClick={() => setActiveCategory(c.id)} data-testid={`category-tab-${c.id}`}>
            {c.name}
          </button>
        ))}
      </div>
      {menus.length === 0 ? (
        <div className="empty-state">
          <div className="icon">🍽️</div>
          <p>메뉴가 없습니다</p>
        </div>
      ) : (
        <div className="menu-grid">
          {menus.map(m => (
            <div key={m.id} className="menu-card" data-testid={`menu-card-${m.id}`}>
              <img src={m.imageUrl || 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400&h=300&fit=crop'}
                   alt={m.name} />
              <div className="menu-card-body">
                <div className="menu-card-name">{m.name}</div>
                <div className="menu-card-desc">{m.description}</div>
                <div className="menu-card-price">{m.price?.toLocaleString()}원</div>
                <button className="menu-card-btn" onClick={() => addToCart(m)}
                        data-testid={`add-to-cart-${m.id}`}>
                  담기
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
      <CustomerNav />
    </div>
  );
}
