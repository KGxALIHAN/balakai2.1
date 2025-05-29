import { Select, Segmented } from 'antd';
import { useDataContext } from '../../contexts/DataContext';

const Header = () => {
  const { year, setBaseYear } = useDataContext();

  const handleSegmentChange = (val: string) => {
    if (val === 'Сейчас') setBaseYear('2025');
    else if (val === 'Через год') setBaseYear('2026');
    else if (val === 'Через 5 лет') setBaseYear('2030');
    else if (val === 'Через 10 лет') setBaseYear('2035');
  };

  const isSelectDisabled = parseInt(year) > 2025;

  return (
    <div className="flex justify-between items-center px-10 py-4 bg-white border-b border-gray-200 h-16 sticky top-0 z-10">
      <div className="text-2xl font-bold text-gray-900">
        Balak<span className="text-purple-400">Ai</span>
      </div>

      <Segmented
        options={['Сейчас', 'Через год', 'Через 5 лет', 'Через 10 лет']}
        onChange={(val) => handleSegmentChange(val.toString())}
        className="bg-gray-100 rounded-4xl"
      />

      <Select
        value={year}
        onChange={(val) => setBaseYear(val)}
        options={[
          { value: '2024', label: '2024 г' },
          { value: '2023', label: '2023 г' },
          { value: '2022', label: '2022 г' },
        ]}
        className="w-28 rounded-3xl"
        disabled={isSelectDisabled}
      />
    </div>
  );
};

export default Header;
