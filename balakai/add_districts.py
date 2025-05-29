import os
import django

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'balakai.settings')
django.setup()

from core.models import District

districts = [
    "Ленинский",
    "Октябрьский",
    "Первомайский",
    "Свердловский",
]

for district_name in districts:
    District.objects.get_or_create(name=district_name)

print("Районы успешно добавлены!")