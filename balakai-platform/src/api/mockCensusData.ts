export interface CensusData {
  year: string;
  capacities: {
    schools: number;
    kindergartens: number;
  };
  ageGroups: {
    name: string;
    value: number;
    fill: string;
    description: string;
  }[];
  schools: {
    id: number;
    name: string;
    status: "balanced" | "filled" | "overloaded";
    description: string;
    position: [number, number];
  }[];
  kindergartens: {
    id: number;
    name: string;
    status: "balanced" | "filled" | "overloaded";
    description: string;
    position: [number, number];
  }[];
  recommendations: {
    count: number;
    unit: string;
    title: string;
    description: string;
    analysisBasis:string;
  }[];
}

export const mockCensusDataByYear: CensusData[] = [
  {
    year: "2022",
    capacities: { schools: 900_000, kindergartens: 350_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 140_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 170_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 210_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 300_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 350_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 370_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "balanced",
        description: "85% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "filled",
        description: "95% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "overloaded",
        description: "110% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "filled",
        description: "90% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "120% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "95% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "115% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 7,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Из-за высокой загрузки некоторых школ",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",
      },
      {
        count: 5,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Для покрытия дефицита мест в дошкольных учреждениях",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",
      },
    ],
  },
  {
    year: "2023",
    capacities: { schools: 920_000, kindergartens: 360_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 142_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 172_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 215_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 310_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 360_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 380_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "filled",
        description: "92% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "105% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "90% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "80% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "125% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "97% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "118% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 8,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Растущая нагрузка требует расширения",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",
      },
      {
        count: 5,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Рост числа детей дошкольного возраста",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",
      },
    ],
  },
  {
    year: "2024",
    capacities: { schools: 940_000, kindergartens: 370_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 145_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 175_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 220_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 320_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 370_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 390_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "overloaded",
        description: "110% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "115% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "95% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "85% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "130% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "100% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "120% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 9,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Серьёзный дефицит мест в некоторых школах",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
      {
        count: 6,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Дефицит в дошкольном образовании",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
    ],
  },
  {
    year: "2025",
    capacities: { schools: 960_000, kindergartens: 380_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 147_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 178_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 225_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 330_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 380_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 400_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "overloaded",
        description: "115% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "120% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "95% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "88% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "135% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "102% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "125% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 10,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Значительный дефицит по школам",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
      {
        count: 7,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Растущий спрос на дошкольные учреждения",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
    ],
  },
  {
    year: "2026",
    capacities: { schools: 980_000, kindergartens: 390_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 150_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 180_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 230_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 340_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 390_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 410_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "overloaded",
        description: "118% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "122% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "98% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "90% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "140% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "105% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "130% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 11,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Увеличение нагрузки требует расширения",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
      {
        count: 8,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Дефицит дошкольных мест",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
    ],
  },
  {
    year: "2030",
    capacities: { schools: 1_050_000, kindergartens: 420_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 160_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 190_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 240_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 360_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 410_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 430_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "overloaded",
        description: "120% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "125% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "100% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "90% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "145% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "110% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "135% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 12,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Серьёзный дефицит по школам",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
      {
        count: 9,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Дефицит дошкольных учреждений",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
    ],
  },
  {
    year: "2035",
    capacities: { schools: 1_100_000, kindergartens: 450_000 },
    ageGroups: [
      {
        name: "0-1 год",
        value: 165_000,
        fill: "#B05CE6",
        description: "Младшая возрастная группа",
      },
      {
        name: "1-3 года",
        value: 195_000,
        fill: "#FF5B5B",
        description: "Растущая группа",
      },
      {
        name: "3-6 лет",
        value: 250_000,
        fill: "#FFB743",
        description: "Основная группа для детсадов",
      },
      {
        name: "7-10 лет",
        value: 380_000,
        fill: "#38C976",
        description: "Начальная школа",
      },
      {
        name: "11-14 лет",
        value: 430_000,
        fill: "#1890FF",
        description: "Средняя школа",
      },
      {
        name: "15-17 лет",
        value: 450_000,
        fill: "#722ED1",
        description: "Старшая школа",
      },
    ],
    schools: [
      {
        id: 1,
        name: "СОШ №1",
        status: "overloaded",
        description: "125% загрузка",
        position: [42.87, 74.59],
      },
      {
        id: 2,
        name: "СОШ №2",
        status: "overloaded",
        description: "130% загрузка",
        position: [42.88, 74.6],
      },
      {
        id: 3,
        name: "СОШ №3",
        status: "filled",
        description: "102% загрузка",
        position: [42.89, 74.61],
      },
      {
        id: 4,
        name: "СОШ №4",
        status: "balanced",
        description: "88% загрузка",
        position: [42.9, 74.62],
      },
    ],
    kindergartens: [
      {
        id: 1,
        name: "Детсад №1",
        status: "overloaded",
        description: "150% загрузка",
        position: [42.86, 74.58],
      },
      {
        id: 2,
        name: "Детсад №2",
        status: "filled",
        description: "112% загрузка",
        position: [42.85, 74.57],
      },
      {
        id: 3,
        name: "Детсад №3",
        status: "overloaded",
        description: "140% загрузка",
        position: [42.84, 74.56],
      },
    ],
    recommendations: [
      {
        count: 13,
        unit: "школ",
        title: "Рекомендуется построить школ",
        description: "Необходимость расширения и строительства новых школ",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
      {
        count: 10,
        unit: "детсадов",
        title: "Рекомендуется построить детсадов",
        description: "Рост потребности в дошкольных местах",
        analysisBasis: "Прогноз основан на численности возрастных групп и текущей загрузке учебных учреждений.",

      },
    ],
  },
];
