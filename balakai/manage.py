#!/usr/bin/env python
"""Django's command-line utility for administrative tasks."""
import os
import sys
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

def main():
    """Run administrative tasks."""
    os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'balakai.settings')
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc:
        raise ImportError(
            "Couldn't import Django. Are you sure it's installed and "
            "available on your PYTHONPATH environment variable? Did you "
            "forget to activate a virtual environment?"
        ) from exc
    execute_from_command_line(sys.argv)


if __name__ == '__main__':
    main()
