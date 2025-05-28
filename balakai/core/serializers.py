from rest_framework import serializers
from .models import District, School, Kindergarten, PopulationStat

class DistrictSerializer(serializers.ModelSerializer):
    class Meta:
        model = District
        fields = '__all__'


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
