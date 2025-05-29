import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  LabelList,
  ResponsiveContainer,
} from "recharts";

const data = [
  { year: "2020", value: 52000 },
  { year: "2021", value: 53500 },
  { year: "2022", value: 54000 },
  { year: "2023", value: 55500 },
  { year: "2024", value: 57000 },
  { year: "2025\nПрогноз", value: 58500 },
];

const BirthrateTrendChart: React.FC = () => {
  return (
    <div className="bg-white rounded-lg shadow-md p-6 w-[1096px]  mx-auto">
      <h2 className="text-lg font-semibold mb-1">
        График тренда рождаемости (2020–2025)
      </h2>
      <p className="text-gray-500 text-sm mb-4">
        Данные переписи населения 2020–2025
      </p>

      <ResponsiveContainer width="100%" height={300}>
        <BarChart
          layout="vertical"
          data={data}
          margin={{ top: 20, right: 30, left: 40, bottom: 10 }}
        >
          <CartesianGrid strokeDasharray="3 3" vertical={false} />
          <XAxis type="number" hide />
          <YAxis
            type="category"
            dataKey="year"
            tick={{ fontSize: 14 }}
            width={90}
          />
          <Tooltip formatter={(value: number) => value.toLocaleString()} />
          <Bar dataKey="value" fill="#3b82f6" background={{ fill: "#f0f0f0" }}>
            <LabelList
              dataKey="value"
              position="right"
              formatter={(value: number) => value.toLocaleString()}
              style={{ fill: "#000", fontSize: 14 }}
            />
          </Bar>
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default BirthrateTrendChart;
