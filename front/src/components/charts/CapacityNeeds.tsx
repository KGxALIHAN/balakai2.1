import { Card, Tooltip } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";
import type { FC } from "react";
import { motion } from "framer-motion";
import { useDataContext } from "../../contexts/DataContext";

const AnimatedNumberFade: FC<{ value: number; locale?: string }> = ({ value, locale = "ru-RU" }) => (
  <motion.span
    key={value}
    initial={{ opacity: 0, y: 6 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ duration: 0.4 }}
  >
    {value.toLocaleString(locale)}
  </motion.span>
);

const CapacityNeeds: FC = () => {
  const { data } = useDataContext();

  if (!data?.capacities) return null;

  return (
    <Card
      className="h-[200px] rounded-lg shadow-sm"
      bodyStyle={{ padding: "16px 20px" }}
    >
      <div className="flex justify-between items-center mb-1">
        <div>
          <h3 className="text-base font-semibold leading-tight">
            Потребности в учебных местах
          </h3>
          <div className="text-xs text-gray-500">
            Данные переписи населения {data.year}г
          </div>
        </div>
        <Tooltip title="Информация о данных">
          <InfoCircleOutlined className="text-gray-400 cursor-pointer" />
        </Tooltip>
      </div>

      <div className="flex rounded-md overflow-hidden mt-3 h-[130px]">
        {/* Школы */}
        <div className="flex-1 flex flex-col items-center justify-center p-3 border-r border-gray-200">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-6 w-6 mb-1 text-gray-700"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            strokeWidth={2}
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v12m-3-6h6" />
            <path strokeLinecap="round" strokeLinejoin="round" d="M18 2H6a2 2 0 00-2 2v16a2 2 0 002 2h12a2 2 0 002-2V4a2 2 0 00-2-2z" />
          </svg>
          <div className="text-gray-600 text-xs">Вместимость школ</div>
          <div className="text-xl font-bold">
            <AnimatedNumberFade value={data.capacities.schools} />
          </div>
        </div>

        {/* Детсады */}
        <div className="flex-1 flex flex-col items-center justify-center p-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-6 w-6 mb-1 text-gray-700"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            strokeWidth={2}
          >
            <circle cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="2" fill="none" />
            <path strokeLinecap="round" strokeLinejoin="round" d="M8 14s1.5 2 4 2 4-2 4-2" />
            <circle cx="9" cy="10" r="1" fill="currentColor" />
            <circle cx="15" cy="10" r="1" fill="currentColor" />
          </svg>
          <div className="text-gray-600 text-xs">Вместимость детсадов</div>
          <div className="text-xl font-bold">
            <AnimatedNumberFade value={data.capacities.kindergartens} />
          </div>
        </div>
      </div>
    </Card>
  );
};

export default CapacityNeeds;
