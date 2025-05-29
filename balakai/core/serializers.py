from rest_framework import serializers
<<<<<<< HEAD
from core.models import District, Forecast, Overload, Recommendation
=======
from .models import District, School, Kindergarten, PopulationStat
>>>>>>> front

class DistrictSerializer(serializers.ModelSerializer):
    class Meta:
        model = District
        fields = '__all__'

<<<<<<< HEAD
class ForecastSerializer(serializers.ModelSerializer):
    district = DistrictSerializer(read_only=True)

    class Meta:
        model = Forecast
        fields = '__all__'

class OverloadSerializer(serializers.ModelSerializer):
    district = DistrictSerializer(read_only=True)

    class Meta:
        model = Overload
        fields = '__all__'

class RecommendationSerializer(serializers.ModelSerializer):
    district = DistrictSerializer(read_only=True)

    class Meta:
        model = Recommendation
        fields = '__all__'
=======

class SchoolSerializer(serializers.ModelSerializer):
    district = DistrictSerializer()

    class Meta:
        model = School
        fields = '__all__'


class KindergartenSerializer(serializers.ModelSerializer):
    district = DistrictSerializer()

    class Meta:
        model = Kindergarten
        fields = '__all__'


class PopulationStatSerializer(serializers.ModelSerializer):
    district = DistrictSerializer()

    class Meta:
        model = PopulationStat
        fields = '__all__'
>>>>>>> front
