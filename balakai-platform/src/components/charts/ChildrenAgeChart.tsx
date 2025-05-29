import {
  RadialBarChart,
  RadialBar,
  ResponsiveContainer,
} from "recharts";
import { Card, Tooltip } from "antd";
import type { FC } from "react";
import { InfoCircleOutlined } from "@ant-design/icons";
import { useDataContext } from "../../contexts/DataContext";
import AnimatedNumberTypewriter from "../../animate/AnimatedNumberFade";

const ChildrenAgeChart: FC = () => {
  const { data } = useDataContext();

  if (!data?.ageGroups) return null;

  const ageData = data.ageGroups;
  const total = ageData.reduce((sum, item) => sum + item.value, 0);

  return (
    <Card
      title="Число детей"
      extra={
        <div className="flex gap-2">
          <span className="text-gray-500 text-sm">
            Данные переписи населения {data.year}г
          </span>
          <Tooltip title="Информация о данных">
            <InfoCircleOutlined className="text-gray-400 cursor-pointer" />
          </Tooltip>
        </div>
      }
      className="w-[456px]"
    >
      <div className="flex flex-col items-center">
        <ResponsiveContainer width="100%" height={250} className="-mb-20">
          <RadialBarChart
            innerRadius="60%"
            outerRadius="100%"
            data={ageData}
            startAngle={180}
            endAngle={0}
          >
            <RadialBar background dataKey="value" />
          </RadialBarChart>
        </ResponsiveContainer>

        <div className="text-3xl font-bold">
          <AnimatedNumberTypewriter value={total} />
        </div>

        <div className="w-full">
          {ageData.map((item, index) => (
            <div
              key={index}
              className="flex justify-between items-start py-1 border-b border-gray-200 last:border-none"
            >
              <div className="flex items-center gap-2">
                <span
                  className="inline-block w-3 h-3 rounded-full"
                  style={{ backgroundColor: item.fill }}
                />
                <div>
                  <div className="font-semibold text-sm">
                    Дети в возрасте {item.name}
                  </div>
                  <div className="text-xs text-gray-500">
                    {item.description}
                  </div>
                </div>
              </div>
              <div className="font-semibold text-sm">
                <AnimatedNumberTypewriter value={item.value} />
              </div>
            </div>
          ))}
        </div>
      </div>
    </Card>
  );
};

export default ChildrenAgeChart;
