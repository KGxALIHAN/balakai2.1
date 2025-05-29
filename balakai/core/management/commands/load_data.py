from django.core.management.base import BaseCommand
import json
from django.contrib.gis.geos import Polygon, Point
from core.models import District, PopulationStat, School, Kindergarten

class Command(BaseCommand):
    help = 'Load data from JSON file'
    def add_arguments(self, parser): parser.add_argument('json_file', type=str)
    def handle(self, *args, **options):
        with open(options['json_file']) as f: data = json.load(f)
        for district_data in data.get('districts', []):
            geometry = Polygon(district_data['geometry']['coordinates'])
            district = District.objects.create(name=district_data['name'], geometry=geometry)
            for pop_data in district_data.get('population_stats', []):
                PopulationStat.objects.create(district=district, year=pop_data['year'], total_population=pop_data['total_population'], school_age_population=pop_data['school_age_population'], preschool_age_population=pop_data['preschool_age_population'])
            for school_data in district_data.get('schools', []):
                location = Point(school_data['location']['coordinates'])
                School.objects.create(name=school_data['name'], district=district, location=location, capacity=school_data['capacity'], current_load=school_data['current_load'])
            for kinder_data in district_data.get('kindergartens', []):
                location = Point(kinder_data['location']['coordinates'])
                Kindergarten.objects.create(name=kinder_data['name'], district=district, location=location, capacity=kinder_data['capacity'], current_load=kinder_data['current_load'])