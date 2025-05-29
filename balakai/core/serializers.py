from rest_framework import serializers
from core.models import District, Forecast, Overload, Recommendation

class DistrictSerializer(serializers.ModelSerializer):
    class Meta:
        model = District
        fields = '__all__'

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