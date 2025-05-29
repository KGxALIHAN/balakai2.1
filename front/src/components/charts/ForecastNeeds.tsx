import { Card, Tooltip, Modal } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";
import React, { useState } from "react";
import { useDataContext } from "../../contexts/DataContext";

const ForecastNeeds: React.FC = () => {
  const { data } = useDataContext();

  const [isModalVisible, setIsModalVisible] = useState(false);
  const [modalContent, setModalContent] = useState({ title: "", description: "" });

  if (!data?.recommendations) return null;

  const showModal = (title: string, description: string) => {
    setModalContent({ title, description });
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <>
      <Card className="p-6 rounded-lg" bodyStyle={{ padding: 0 }} bordered={false}>
        <div className="mb-4 flex items-center justify-between gap-2 m-6">
          <h3 className="text-lg font-semibold">Прогноз потребности в местах</h3>
          <Tooltip
            placement="right"
            title="Данный прогноз был рассчитан исходя из данных о рождаемости и вместимости школ"
          >
            <InfoCircleOutlined className="text-gray-500 cursor-pointer" />
          </Tooltip>
        </div>

        <div className="mb-2 text-sm text-gray-600 m-6">
          Сколько учебных мест требуется ({data.year}г)
        </div>

        <div className="flex flex-col gap-6 m-6">
          {data.recommendations.map(({ count, unit, title, description, analysisBasis  }, idx) => (
            <div key={idx} className="flex items-start justify-between">
              <div className="flex flex-col gap-0.5">
                <div className="flex items-center gap-2">
                  <span className="bg-orange-300 text-orange-900 px-2 py-0.5 rounded-sm text-sm font-semibold">
                    {count.toLocaleString("ru-RU")} {unit}
                  </span>
                  <span className="font-semibold text-base">{title}</span>
                </div>
                <div className="text-xs text-gray-500">{description}</div>
              </div>

              <button
                type="button"
                aria-label="Совет"
                className="bg-gradient-to-br from-red-400 to-orange-400 p-2 rounded-full shadow-md hover:brightness-110 transition"
                onClick={() => showModal(title, analysisBasis)}
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="white"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="white"
                  className="w-5 h-5"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M11.25 3v1.5m0 13.5v1.5m7.5-7.5h-1.5m-13.5 0H3m15.364 6.364l-1.06-1.06m-10.607 0l-1.06 1.06m0-10.607l1.06 1.06m10.607 0l1.06-1.06M12 7.5a4.5 4.5 0 100 9 4.5 4.5 0 000-9z"
                  />
                </svg>
              </button>
            </div>
          ))}
        </div>
      </Card>

      <Modal
        title={modalContent.title}
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        okText="Понятно"
        cancelText="Закрыть"
      >
        <p>{modalContent.description}</p>
      </Modal>
    </>
  );
};

export default ForecastNeeds;
