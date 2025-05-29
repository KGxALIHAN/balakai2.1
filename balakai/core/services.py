from sklearn.linear_model import LinearRegression
import numpy as np
from core.models import PopulationStat, School, Kindergarten

def forecast_population(district, years=[1, 5, 10]):
    stats = PopulationStat.objects.filter(district=district).order_by('year')
    if len(stats) < 2: return {'error': 'Insufficient data for forecast'}
    years_data = np.array([s.year for s in stats]).reshape(-1, 1)
    school_age = np.array([s.school_age_population for s in stats])
    preschool_age = np.array([s.preschool_age_population for s in stats])
    model_school = LinearRegression().fit(years_data, school_age)
    model_preschool = LinearRegression().fit(years_data, preschool_age)
    future_years = np.array([2025 + y for y in years]).reshape(-1, 1)
    school_forecast = model_school.predict(future_years).tolist()
    preschool_forecast = model_preschool.predict(future_years).tolist()
    return {'district': district.name, 'forecast': {f'year_{y}': {'school_age': int(s), 'preschool_age': int(p)} for y, s, p in zip(years, school_forecast, preschool_forecast)}}

def analyze_overload(district):
    schools = School.objects.filter(district=district)
    kindergartens = Kindergarten.objects.filter(district=district)
    school_load = sum(s.current_load / s.capacity * 100 for s in schools) / (len(schools) or 1)
    kinder_load = sum(k.current_load / k.capacity * 100 for k in kindergartens) / (len(kindergartens) or 1)
    return {'district': district.name, 'school_load_percent': round(school_load, 2), 'kindergarten_load_percent': round(kinder_load, 2), 'school_overload': school_load > 100, 'kindergarten_overload': kinder_load > 100}

def generate_recommendations(district):
    forecast = forecast_population(district)
    if 'error' in forecast: return {'error': forecast['error']}
    school_age_10y = forecast['forecast']['year_10']['school_age']
    preschool_age_10y = forecast['forecast']['year_10']['preschool_age']
    schools_needed = max(0, (school_age_10y // 1200) - School.objects.filter(district=district).count())
    kinders_needed = max(0, (preschool_age_10y // 300) - Kindergarten.objects.filter(district=district).count())
    return {'district': district.name, 'schools_needed': schools_needed, 'kindergartens_needed': kinders_needed}