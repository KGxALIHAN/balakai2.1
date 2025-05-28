from django.db import models

class District(models.Model):
    name = models.CharField(max_length=100)
    population = models.IntegerField(default=0)
    geojson = models.JSONField(null=True, blank=True)

    def __str__(self):
        return self.name


class School(models.Model):
    name = models.CharField(max_length=255)
    district = models.ForeignKey(District, on_delete=models.CASCADE)
    capacity = models.IntegerField()
    current_students = models.IntegerField()
    location = models.JSONField(null=True, blank=True)  # GeoJSON точка

    def __str__(self):
        return self.name


class Kindergarten(models.Model):
    name = models.CharField(max_length=255)
    district = models.ForeignKey(District, on_delete=models.CASCADE)
    capacity = models.IntegerField()
    current_children = models.IntegerField()
    location = models.JSONField(null=True, blank=True)

    def __str__(self):
        return self.name


class PopulationStat(models.Model):
    district = models.ForeignKey(District, on_delete=models.CASCADE)
    year = models.IntegerField()
    total = models.IntegerField()
    age_0_6 = models.IntegerField()
    age_7_17 = models.IntegerField()

    class Meta:
        unique_together = ('district', 'year')

    def __str__(self):
        return f"{self.district.name} - {self.year}"
