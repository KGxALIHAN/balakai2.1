import React, { useState } from "react";
import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  ZoomControl,
} from "react-leaflet";
import L from "leaflet";
import { Segmented, Tooltip } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";
import { useDataContext } from "../../contexts/DataContext";

const redIconUrl = "/icons/marker-icon-red.svg";
const orangeIconUrl = "/icons/marker-icon-orange.svg";
const greenIconUrl = "/icons/marker-icon-green.svg";

const createIcon = (iconUrl: string) =>
  new L.Icon({
    iconUrl,
    iconSize: [40, 65],
    iconAnchor: [20, 65],
    popupAnchor: [1, -55],
    shadowSize: [65, 65],
  });

const redIcon = createIcon(redIconUrl);
const orangeIcon = createIcon(orangeIconUrl);
const greenIcon = createIcon(greenIconUrl);

const getIconByStatus = (status: string) => {
  switch (status) {
    case "overloaded":
      return redIcon;
    case "filled":
      return orangeIcon;
    case "balanced":
      return greenIcon;
    default:
      return greenIcon;
  }
};

const statusToText: Record<string, string> = {
  overloaded: "Перегружено",
  filled: "Заполнено",
  balanced: "Сбалансировано",
};

const statusToColor: Record<string, string> = {
  overloaded: "red",
  filled: "orange",
  balanced: "green",
};

const MapWidget: React.FC = () => {
  const { data } = useDataContext();

  const [activeTab, setActiveTab] = useState<"schools" | "kindergartens">(
    "schools"
  );

  const schools = data?.schools ?? [];
  const kindergartens = data?.kindergartens ?? [];

  const markers = activeTab === "schools" ? schools : kindergartens;

  return (
    <div className="bg-white rounded-lg shadow-md p-6 w-[1046px] mx-auto">
      <div className="flex justify-between ">
        <div>
          <h2 className="text-lg font-semibold mb-1">
            Потребности в учебных местах
          </h2>
          <p className="text-gray-500 text-sm mb-4">
            Данные о загруженности школ, детских садов и библиотек
          </p>
        </div>
        <Tooltip title="Информация о данных">
          <InfoCircleOutlined className="text-gray-400 cursor-pointer" />
        </Tooltip>
      </div>

      {/* Табсы */}
      <Segmented
        options={[
          { label: "Школы", value: "schools" },
          { label: "Детские сады", value: "kindergartens" },
        ]}
        value={activeTab}
        onChange={(val) => setActiveTab(val as "schools" | "kindergartens")}
        style={{ marginBottom: 16 }}
      />

      {/* Карта */}
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
          {markers.map((item) => (
            <Marker
              key={item.id}
              position={item.position}
              icon={getIconByStatus(item.status)}
            >
              <Popup>
                <div>
                  <b>{item.name}</b>
                  <br />
                  <span
                    style={{
                      color: statusToColor[item.status],
                      fontWeight: "bold",
                    }}
                  >
                    {statusToText[item.status]}
                  </span>{" "}
                  {item.description}
                </div>
              </Popup>
            </Marker>
          ))}
        </MapContainer>
      </div>

      <div className="flex gap-4 items-center text-sm text-gray-700 select-none">
        <LegendItem
          color="red"
          label="Перегружено"
          description="Нехватка мест"
        />
        <LegendItem color="orange" label="Заполнено" description="Мест нет" />
        <LegendItem
          color="green"
          label="Сбалансировано"
          description="Есть места"
        />
      </div>
    </div>
  );
};

interface LegendItemProps {
  color: string;
  label: string;
  description: string;
}
const LegendItem: React.FC<LegendItemProps> = ({
  color,
  label,
  description,
}) => (
  <div className="flex items-center gap-2">
    <span
      className="inline-block w-4 h-4 rounded-full"
      style={{ backgroundColor: color }}
    />
    <span>{label}</span>
    <span className="text-gray-400">{description}</span>
  </div>
);

export default MapWidget;
