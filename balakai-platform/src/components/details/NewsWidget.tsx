import { InfoCircleOutlined, ArrowRightOutlined } from '@ant-design/icons';
import { Card, Typography } from 'antd';

const { Text, Title } = Typography;

const newsItems = [
  {
    title: 'Обновлены данные рождаемости и миграции за 2024 год',
    date: '27.05.2025',
    source: 'kaktus.media',
    link: 'https://kaktus.media',
  },
  {
    title: 'Изменены стандарты площади на одно учебное место (увеличение на 5%)',
    date: '20.05.2025',
    source: 'azattyk.kg',
    link: 'https://azattyk.kg',
  },
  {
    title: 'Запущен пилотный проект по цифровизации учета в школах Чуйской области',
    date: '15.05.2025',
    source: 'sputnik.kg',
    link: 'https://sputnik.kg',
  },
];

const NewsWidget = () => {
  return (
    <Card
      className="max-w-md"
      bodyStyle={{ padding: 16 }}
      style={{ borderRadius: 12 }}
    >
      <div className="flex justify-between items-start mb-4">
        <div>
          <Title level={5} style={{ margin: 0 }}>
            Новости и обновления
          </Title>
          <Text type="secondary" style={{ fontSize: 12 }}>
            Здесь собраны последние новости и обновления
          </Text>
        </div>
        <InfoCircleOutlined style={{ fontSize: 16, color: '#bfbfbf' }} />
      </div>

      <div className="space-y-4">
        {newsItems.map((item, idx) => (
          <a
            key={idx}
            href={item.link}
            target="_blank"
            rel="noopener noreferrer"
            className="block hover:bg-gray-50 px-2 py-2 rounded-lg transition"
          >
            <div className="flex justify-between items-start gap-2">
              <div className="flex-1">
                <Text strong>{item.title}</Text>
                <div className="text-xs text-gray-500 mt-1">
                  {item.date} &nbsp;·&nbsp; {item.source}
                </div>
              </div>
              <ArrowRightOutlined className="text-gray-400 mt-1" />
            </div>
          </a>
        ))}
      </div>
    </Card>
  );
};

export default NewsWidget;
