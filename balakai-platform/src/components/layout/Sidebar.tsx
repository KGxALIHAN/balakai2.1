import { Layout, Menu } from "antd";
import { useNavigate, useLocation } from "react-router-dom";

const { Sider } = Layout;

const Sidebar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const selectedKey = location.pathname.replace("/", "") || "dashboard";

  return (
    <Sider
      width={300}
      className="bg-white shadow-sm p-5"
      style={{
        background: "#fff",
      }}
    >
      <Menu
        mode="inline"
        selectedKeys={[selectedKey]}
        style={{ borderRight: 0 }}
        onClick={({ key }) => {
          navigate(`/${key}`);
        }}
        items={[
          { key: "dashboard", label: "Обзорная панель" },
          { key: "details", label: "Интерактивная карта" },
        ]}
      />
    </Sider>
  );
};

export default Sidebar;
