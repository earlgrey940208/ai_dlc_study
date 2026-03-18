import { Routes, Route, Navigate } from 'react-router-dom';
import CustomerLogin from './pages/customer/CustomerLogin';
import MenuPage from './pages/customer/MenuPage';
import CartPage from './pages/customer/CartPage';
import OrderHistoryPage from './pages/customer/OrderHistoryPage';
import AdminLogin from './pages/admin/AdminLogin';
import Dashboard from './pages/admin/Dashboard';
import TableManagement from './pages/admin/TableManagement';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/customer/login" />} />
      <Route path="/customer/login" element={<CustomerLogin />} />
      <Route path="/customer/menu" element={<MenuPage />} />
      <Route path="/customer/cart" element={<CartPage />} />
      <Route path="/customer/orders" element={<OrderHistoryPage />} />
      <Route path="/admin/login" element={<AdminLogin />} />
      <Route path="/admin/dashboard" element={<Dashboard />} />
      <Route path="/admin/tables" element={<TableManagement />} />
    </Routes>
  );
}
