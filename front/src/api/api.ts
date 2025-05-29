// api.ts
import allData from './data.json';
import type { CensusData } from './mockCensusData';

export interface FetchCensusParams {
  year: string;
}

export const fetchCensusData = async (params: FetchCensusParams): Promise<CensusData | null> => {
  console.log('Запрос данных из JSON с параметрами:', params);

  // Просто фильтруем импортированный JSON по году
  const data = allData.find((item: CensusData) => item.year === params.year) ?? null;

  return data;
};
