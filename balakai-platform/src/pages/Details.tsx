import BirthrateTrendChart from "../components/details/BirthrateTrendChart";
import DetailsMapWidget from "../components/details/DetailsMapWidget";
import EducationBudgetGauge from "../components/details/EducationBudgetGauge";
import NewsWidget from "../components/details/NewsWidget";
import { useDataContext } from "../contexts/DataContext";

const Details = () => {
  const { data } = useDataContext();

  if (!data?.capacities) return <div className="text-4xl">Загрузка....</div>;
  return (
    <>
      <h1 className="text-4xl font-extrabold">Демографический исследователь</h1>
      <p className="text-[16px]">
        Визуализация демографических данных и прогнозов по регионам и возрастным
        группам
      </p>
      <div className="flex flex-col gap-10">
        <DetailsMapWidget />
        <BirthrateTrendChart />
        <div className="flex justify-center gap-28">
          <EducationBudgetGauge/>
          <NewsWidget/>
        </div>
      </div>
    </>
  );
};

export default Details;
