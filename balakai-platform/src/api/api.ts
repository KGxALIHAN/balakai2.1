export interface FetchCensusParams {
  year: string;
}

export interface CensusData {
  year: string;
  birthrate: number;
  children: number;
  capacity: number;
  // добавь остальные поля, если есть
}

export const fetchCensusData = async (params: FetchCensusParams): Promise<CensusData | null> => {
  try {
    const response = await fetch(`http://26.139.74.122:8000/api/forecast/1`);

    if (!response.ok) {
      console.error('Ошибка при запросе данных:', response.statusText);
      return null;
    }

    const data: CensusData = await response.json();
    return data;
  } catch (error) {
    console.error('Ошибка при загрузке данных:', error);
    return null;
  }
};
