import { Routes, Route, Navigate } from 'react-router-dom';
import MainLayout from './components/layout/MainLayout';
import Dashboard from './pages/Dashboard';
import Details from './pages/Details';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<Navigate to="/dashboard" />} />
        <Route path="dashboard" element={<Dashboard />} />
        <Route path="details" element={<Details />} />
      </Route>

      <Route path="*" element={<div className="p-10 text-center">Страница не найдена</div>} />
    </Routes>
  );
};

export default App;
