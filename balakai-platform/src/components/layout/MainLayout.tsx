import { Layout } from "antd";
import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import Header from "./Header";

const { Content } = Layout;

const MainLayout = () => (
  <Layout className="min-h-screen">
    <Header /> 
    <Layout className="flex">
      <Sidebar />
      <Layout className="flex-1">
        <Content className="p-6 bg-gray-50 h-[calc(100vh-64px)] overflow-auto">
          <Outlet />
        </Content>
      </Layout>
    </Layout>
  </Layout>
);

export default MainLayout;
