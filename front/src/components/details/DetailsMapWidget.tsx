import React, { useEffect, useState } from "react";
import {
  MapContainer,
  TileLayer,
  ZoomControl,
  useMap,
} from "react-leaflet";
import L from "leaflet";
import "leaflet.heat";
import { Segmented, Tooltip } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";

interface HeatmapLayerWrapperProps {
  points: [number, number, number][];
}

const HeatmapLayerWrapper: React.FC<HeatmapLayerWrapperProps> = ({ points }) => {
  const map = useMap();

  useEffect(() => {
    const heatLayer = (L as any).heatLayer(points, {
      radius: 60,
      blur: 50,
      maxZoom: 10,
      gradient: {
        0.0: '#ffcccc',
        0.4: '#ff6666',
        0.7: '#ff1a1a', 
        1.0: '#b30000',
      },
    }).addTo(map);

    return () => {
      map.removeLayer(heatLayer);
    };
  }, [points, map]);

  return null;
};

type TabKey = "birthrate" | "children" | "capacity";

const DetailsMapWidget: React.FC = () => {
  const [activeTab, setActiveTab] = useState<TabKey>("birthrate");

  const heatmapData: Record<TabKey, [number, number, number][]> = {
    birthrate: [
      [42.8746, 74.6122, 0.9],
      [42.8846, 74.6022, 0.7],
      [42.8646, 74.5922, 0.4],
      [42.8546, 74.5822, 0.2],
    ],
    children: [
      [42.8746, 74.6122, 0.6],
      [42.8946, 74.6222, 0.8],
      [42.8646, 74.5922, 0.3],
    ],
    capacity: [
      [42.8746, 74.6122, 0.4],
      [42.8946, 74.6222, 0.6],
      [42.8846, 74.6322, 0.9],
    ],
  };

  return (
    <div className="bg-white rounded-lg shadow-md w-[1096px] p-6 mx-auto">
      <div className="flex justify-between items-start">
        <div>
          <h2 className="text-lg font-semibold mb-1">
            Потребности в учебных местах
          </h2>
          <p className="text-gray-500 text-sm mb-4">
            Данные о загруженности школ, детских садов и библиотек
          </p>
        </div>
        <Tooltip title="Источник данных: Министерство образования, 2024">
          <InfoCircleOutlined className="text-gray-500 text-xl hover:text-blue-500 transition" />
        </Tooltip>
      </div>

      <Segmented
        options={[
          { label: "Рождаемость", value: "birthrate" },
          { label: "Численность детей", value: "children" },
          { label: "Вместимость", value: "capacity" },
        ]}
        value={activeTab}
        onChange={(val) => setActiveTab(val as TabKey)}
        style={{ marginBottom: 16 }}
      />

      <div className="w-full h-[400px] rounded-md overflow-hidden mb-4">
        <MapContainer
          center={[42.8746, 74.6122]}
          zoom={13}
          scrollWheelZoom={false}
          zoomControl={false}
          style={{ width: "100%", height: "100%" }}
        >
          <ZoomControl position="topright" />
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <HeatmapLayerWrapper points={heatmapData[activeTab]} />
        </MapContainer>
      </div>

      <div className="flex flex-wrap gap-4 text-sm text-gray-700 select-none">
        {activeTab === "birthrate" && (
          <>
            <LegendItem color="#ff0000" label="Высокая рождаемость" description="100 детей на 1000 человек" />
            <LegendItem color="#ff8000" label="Средняя рождаемость" description="50 детей на 1000 человек" />
            <LegendItem color="#0055ff" label="Низкая рождаемость" description="Меньше 25 детей на 1000 человек" />
          </>
        )}
        {activeTab === "children" && (
          <>
            <LegendItem color="#00ffff" label="Много детей" description="Более 1000" />
            <LegendItem color="#00ff00" label="Средне" description="500–1000" />
            <LegendItem color="#cccccc" label="Мало" description="Менее 500" />
          </>
        )}
        {activeTab === "capacity" && (
          <>
            <LegendItem color="#ff00ff" label="Недостаточно мест" description="Загрузка > 90%" />
            <LegendItem color="#ffff00" label="Почти заполнено" description="70–90%" />
            <LegendItem color="#00ff7f" label="Достаточно мест" description="< 70%" />
          </>
        )}
      </div>
    </div>
  );
};

interface LegendItemProps {
  color: string;
  label: string;
  description: string;
}

const LegendItem: React.FC<LegendItemProps> = ({ color, label, description }) => (
  <div className="flex items-center gap-2 bg-gray-50 px-2 py-1 rounded-md shadow-sm">
    <span
      className="inline-block w-4 h-4 rounded-full border border-gray-300"
      style={{ backgroundColor: color }}
    />
    <div className="flex flex-col leading-tight">
      <span className="font-medium">{label}</span>
      <span className="text-xs text-gray-400">{description}</span>
    </div>
  </div>
);

export default DetailsMapWidget;
