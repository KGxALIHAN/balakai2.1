from sklearn.linear_model import LinearRegression
import numpy as np
from .models import PopulationStat, School, Kindergarten, District

# Нормативы
SCHOOL_CAPACITY = 1200
KINDERGARTEN_CAPACITY = 300

def forecast_population(district, years=10):
    """
    Делает простой линейный прогноз численности детей по возрастным группам.
    """
    stats = PopulationStat.objects.filter(district=district).order_by('year')
    years_list = []
    age_7_17 = []
    age_0_6 = []

    for stat in stats:
        years_list.append(stat.year)
        age_7_17.append(stat.age_7_17)
        age_0_6.append(stat.age_0_6)

    if len(years_list) < 2:
        return None  # Не хватает данных

    X = np.array(years_list).reshape(-1, 1)
    model_school = LinearRegression().fit(X, age_7_17)
    model_kinder = LinearRegression().fit(X, age_0_6)

    forecasts = []
    for i in range(1, years + 1):
        future_year = years_list[-1] + i
        school_count = model_school.predict([[future_year]])[0]
        kinder_count = model_kinder.predict([[future_year]])[0]
        forecasts.append({
            "year": future_year,
            "age_7_17": int(school_count),
            "age_0_6": int(kinder_count)
        })

    return forecasts


def calculate_overload(district):
    """
    Возвращает процент перегрузки школ и садиков.
    """
    schools = School.objects.filter(district=district)
    kinders = Kindergarten.objects.filter(district=district)

    school_capacity = sum(s.capacity for s in schools)
    school_load = sum(s.current_students for s in schools)

    kinder_capacity = sum(k.capacity for k in kinders)
    kinder_load = sum(k.current_children for k in kinders)

    overload_school = (school_load - school_capacity) / school_capacity * 100 if school_capacity else 0
    overload_kinder = (kinder_load - kinder_capacity) / kinder_capacity * 100 if kinder_capacity else 0

    return {
        "schools": {
            "capacity": school_capacity,
            "students": school_load,
            "overload_percent": round(overload_school, 2)
        },
        "kindergartens": {
            "capacity": kinder_capacity,
            "children": kinder_load,
            "overload_percent": round(overload_kinder, 2)
        }
    }


def generate_recommendations(district, forecast_years=5):
    """
    Генерирует рекомендации, сколько школ/садов нужно построить.
    """
    forecast = forecast_population(district, years=forecast_years)
    if not forecast:
        return None

    last = forecast[-1]
    school_needed = max(0, int((last["age_7_17"] - SCHOOL_CAPACITY_TOTAL(district)) / SCHOOL_CAPACITY))
    kinder_needed = max(0, int((last["age_0_6"] - KINDER_CAPACITY_TOTAL(district)) / KINDERGARTEN_CAPACITY))

    return {
        "recommendation_year": last["year"],
        "schools_needed": school_needed,
        "kindergartens_needed": kinder_needed
    }

def SCHOOL_CAPACITY_TOTAL(district):
    return sum(s.capacity for s in School.objects.filter(district=district))

def KINDER_CAPACITY_TOTAL(district):
    return sum(k.capacity for k in Kindergarten.objects.filter(district=district))
