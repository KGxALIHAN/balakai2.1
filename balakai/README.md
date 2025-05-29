# BalakAI Backend

Backend for the BalakAI hackathon project, providing a REST API for population forecasting, school/kindergarten overload analysis, and infrastructure recommendations in Bishkek.

## Requirements
- Python 3.10+
- PostgreSQL 14+ with PostGIS extension
- Windows (tested on Windows 10/11)

## Setup Instructions
1. Install PostgreSQL and PostGIS:
   - Download from [postgresql.org](https://www.postgresql.org/download/windows/).
   - Install PostGIS from [postgis.net](http://download.osgeo.org/postgis/windows/).
   - Create database: `CREATE DATABASE balakai; \c balakai; CREATE EXTENSION postgis;`
2. Install GDAL:
   - Download wheel from [lfd.uci.edu/~gohlke/pythonlibs/#gdal](https://www.lfd.uci.edu/~gohlke/pythonlibs/#gdal).
   - Install: `pip install GDAL-3.6.2-cp311-cp311-win_amd64.whl`.
   - Add to PATH: `C:\Program Files\PostgreSQL\15\gdal-data`.
   - Set `GDAL_LIBRARY_PATH=C:\Program Files\PostgreSQL\15\bin\libgdal.dll`.
3. Setup Virtual Environment:
   ```bash
   python -m venv venv
   venv\Scripts\activate
   pip install -r requirements.txt