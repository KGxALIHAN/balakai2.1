import React, { createContext, useContext, useState, useEffect } from 'react';
import { fetchCensusData } from '../api/api';

interface DataContextType {
  year: string;           // Текущий выбранный год
  baseYear: string;       // Базовый год, который изменяется
  data: any;
  setBaseYear: (year: string) => void;
  setYear: (year: string) => void;
}

const DataContext = createContext<DataContextType | undefined>(undefined);

export const DataProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [baseYear, setBaseYear] = useState('2025');
  const [year, setYear] = useState('2025');
  const [data, setData] = useState<any>(null);

  useEffect(() => {
    setYear(baseYear); // year теперь напрямую зависит от baseYear

    const load = async () => {
      try {
        const response = await fetchCensusData({ year: baseYear });
        setData(response);
        console.log('🔄 Данные загружены:', response);
      } catch (err) {
        console.error('❌ Ошибка загрузки данных:', err);
      }
    };

    load();
  }, [baseYear]);

  return (
    <DataContext.Provider value={{ year, baseYear, data, setBaseYear, setYear }}>
      {children}
    </DataContext.Provider>
  );
};

export const useDataContext = () => {
  const ctx = useContext(DataContext);
  if (!ctx) throw new Error('useDataContext must be used within a DataProvider');
  return ctx;
};
