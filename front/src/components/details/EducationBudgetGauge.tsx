import React from "react";
import {
  RadialBarChart,
  RadialBar,
  PolarAngleAxis,
  ResponsiveContainer,
} from "recharts";
import { InfoCircleOutlined } from "@ant-design/icons";
import { Tooltip } from "antd";

const total = 18;
const spent = 5;
const data = [
  {
    name: "Расходы на строительство",
    value: spent,
    fill: "#ef4444", // красный
  },
  {
    name: "Бюджет на образование",
    value: total,
    fill: "#3b82f6", // синий
  },
];

const EducationBudgetGauge: React.FC = () => {
  return (
    <div className="bg-white rounded-lg shadow-md p-6 w-full max-w-[545px]">
      <div className="flex justify-between items-start mb-4">
        <div>
          <h2 className="text-lg font-semibold">Бюджет на образование</h2>
          <p className="text-gray-500 text-sm">Данные от Министерства финансов</p>
        </div>
        <Tooltip title="Бюджет на 2025 год">
          <InfoCircleOutlined className="text-gray-400 cursor-pointer" />
        </Tooltip>
      </div>

      <ResponsiveContainer width="100%" height={260} className={'-mt-36'}>
        <RadialBarChart
          cx="50%"
          cy="100%" 
          innerRadius="80%"
          outerRadius="100%"
          startAngle={180}
          endAngle={0}
          barSize={20}
          data={data}
        >
          <PolarAngleAxis
            type="number"
            domain={[0, total]}
            tick={false}
          />
          <RadialBar
            background
            dataKey="value"
            cornerRadius={10}
          />
        </RadialBarChart>
      </ResponsiveContainer>

      <div className="text-sm space-y-2 flex flex-col gap-2 mt-10">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <span className="inline-block w-3 h-3 rounded-full bg-blue-500" />
            <span>Бюджет на образование</span>
          </div>
          <span className="font-medium">18 млрд KGS</span>
        </div>
        <hr className="text-gray-300"/>
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <span className="inline-block w-3 h-3 rounded-full bg-red-500" />
            <span>Расходы на строительство новых учебных заведений</span>
          </div>
          <span className="font-medium">5 млрд KGS</span>
        </div> 
      </div>
    </div>
  );
};

export default EducationBudgetGauge;
