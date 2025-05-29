from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from core.serializers import DistrictSerializer, ForecastSerializer, OverloadSerializer, RecommendationSerializer
from core.models import District, Forecast, Overload, Recommendation

class ForecastView(APIView):
    def get(self, request, district_id):
        try:
            district = District.objects.get(id=district_id)
            forecasts = Forecast.objects.filter(district=district)
            serializer = ForecastSerializer(forecasts, many=True)
            return Response(serializer.data, status=status.HTTP_200_OK)
        except District.DoesNotExist:
            return Response({"error": "District not found"}, status=status.HTTP_404_NOT_FOUND)

class OverloadView(APIView):
    def get(self, request, district_id):
        try:
            district = District.objects.get(id=district_id)
            overloads = Overload.objects.filter(district=district)
            serializer = OverloadSerializer(overloads, many=True)
            return Response(serializer.data, status=status.HTTP_200_OK)
        except District.DoesNotExist:
            return Response({"error": "District not found"}, status=status.HTTP_404_NOT_FOUND)

class RecommendationsView(APIView):
    def get(self, request, district_id):
        try:
            district = District.objects.get(id=district_id)
            recommendations = Recommendation.objects.filter(district=district)
            serializer = RecommendationSerializer(recommendations, many=True)
            return Response(serializer.data, status=status.HTTP_200_OK)
        except District.DoesNotExist:
            return Response({"error": "District not found"}, status=status.HTTP_404_NOT_FOUND)

class MapDataView(APIView):
    def get(self, request):
        districts = District.objects.all()
        serializer = DistrictSerializer(districts, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)