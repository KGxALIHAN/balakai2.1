from django.contrib import admin
from django.urls import path
from core.views import ForecastView, OverloadView, RecommendationsView, MapDataView
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/forecast/<int:district_id>/', ForecastView.as_view(), name='forecast'),
    path('api/overload/<int:district_id>/', OverloadView.as_view(), name='overload'),
    path('api/recommendations/<int:district_id>/', RecommendationsView.as_view(), name='recommendations'),
    path('api/map-data/', MapDataView.as_view(), name='map-data'),
    path('api/docs/schema/', SpectacularAPIView.as_view(), name='schema'),
    path('api/docs/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger-ui'),
]