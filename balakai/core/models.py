from django.db import models

class District(models.Model):
<<<<<<< HEAD
    name = models.CharField(max_length=100, verbose_name="Название района")
    polygon = models.TextField(
        null=True, blank=True, verbose_name="Полигон района (временное текстовое поле)"
    )

    class Meta:
        verbose_name = "Район"
        verbose_name_plural = "Районы"
=======
    name = models.CharField(max_length=100)
    population = models.IntegerField(default=0)
    geojson = models.JSONField(null=True, blank=True)
>>>>>>> front

    def __str__(self):
        return self.name


<<<<<<< HEAD
class Forecast(models.Model):
    district = models.ForeignKey(
        District, on_delete=models.CASCADE, related_name="forecasts", verbose_name="Район"
    )
    date = models.DateField(verbose_name="Дата прогноза")
    temperature = models.FloatField(verbose_name="Температура (°C)")
    precipitation = models.FloatField(verbose_name="Осадки (мм)", default=0.0)
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="Дата создания")

    class Meta:
        verbose_name = "Прогноз"
        verbose_name_plural = "Прогнозы"

    def __str__(self):
        return f"Прогноз для {self.district} на {self.date}"


class Overload(models.Model):
    district = models.ForeignKey(
        District, on_delete=models.CASCADE, related_name="overloads", verbose_name="Район"
    )
    date = models.DateField(verbose_name="Дата")
    overload_level = models.IntegerField(
        verbose_name="Уровень перегрузки (1-10)", choices=[(i, str(i)) for i in range(1, 11)]
    )
    description = models.TextField(
        null=True, blank=True, verbose_name="Описание перегрузки"
    )
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="Дата создания")

    class Meta:
        verbose_name = "Перегрузка"
        verbose_name_plural = "Перегрузки"

    def __str__(self):
        return f"Перегрузка в {self.district} на {self.date}"


class Recommendation(models.Model):
    district = models.ForeignKey(
        District,
        on_delete=models.CASCADE,
        related_name="recommendations",
        verbose_name="Район",
    )
    date = models.DateField(verbose_name="Дата рекомендации")
    recommendation_text = models.TextField(verbose_name="Рекомендация")
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="Дата создания")

    class Meta:
        verbose_name = "Рекомендация"
        verbose_name_plural = "Рекомендации"

    def __str__(self):
        return f"Рекомендация для {self.district} на {self.date}"
=======
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
>>>>>>> front
