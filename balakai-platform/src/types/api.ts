// Типы данных

export type SchoolStatus = 'overloaded' | 'filled' | 'balanced';

export interface Institution {
  id: number;
  name: string;
  status: SchoolStatus;
  description: string; // например "100%"
  position: [number, number]; // [lat, lng]
}

export interface CapacityData {
  schools: number;
  kindergartens: number;
}

export interface AgeGroup {
  name: string;
  value: number;
  fill: string;
  description: string;
}

export interface Recommendation {
  count: number;
  unit: string;
  title: string;
  description: string;
}

export interface CensusDataResponse {
  year: string;
  period: string;
  capacities: CapacityData;
  ageGroups: AgeGroup[];
  schools: Institution[];
  kindergartens: Institution[];
  recommendations: Recommendation[];
}

export interface FetchCensusParams {
  year: string;
  period: string;
}