import CapacityNeeds from "../components/charts/CapacityNeeds";
import ChildrenAgeChart from "../components/charts/ChildrenAgeChart";
import ForecastNeeds from "../components/charts/ForecastNeeds";
import Map from "../components/charts/Map";
import { useDataContext } from "../contexts/DataContext";

const DashboardPage = () => {
  const { data } = useDataContext();

  if (!data?.capacities) return <div className="text-4xl">Загрузка....</div>;
  return (
    <>
    <h1 className="text-4xl font-extrabold">Обзорная панель</h1>
    <p className="text-[16px]">Обзор ключевых показателей и текущей ситуации по учебным заведениям</p>
      <div className="flex flex-col gap-10">
        <div className="flex justify-around">
          <ChildrenAgeChart />
          <div className="flex flex-col gap-6 w-[60%]">
            <CapacityNeeds />
            <ForecastNeeds />
          </div>
        </div>
        <Map />
      </div>
    </>
  );
};

export default DashboardPage;
