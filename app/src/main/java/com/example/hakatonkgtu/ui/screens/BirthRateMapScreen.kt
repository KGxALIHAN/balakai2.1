package com.example.hakatonkgtu.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import android.graphics.Color as AndroidColor

@Composable
fun BirthRateMapScreen() {
    val tabs = listOf("Рождаемость", "Численность детей", "Вместимость")
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        Text(
            "Потребности в учебных местах",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Данные о загруженности школ, детских садов и библиотек",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color(0xFFF0F0F0),
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .height(2.dp) // Высота индикатора (линии под вкладкой)
                        .background(Color.Black) // Цвет индикатора
                        .clip(RoundedCornerShape(20.dp))
                )
            },
            edgePadding = 0.dp, // Отступы слева и справа (для первой и последней вкладки)
            divider = {
                Spacer(modifier = Modifier.width(8.dp)) // Зазор между вкладками
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp) // Высота вкладок как на скриншоте
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF0F0F0))
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = selectedTab == index
                val textColor by animateColorAsState(if (selected) Color.Black else Color.DarkGray)
                Tab(
                    selected = selected,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            color = textColor,
                            style = MaterialTheme.typography.bodyMedium // Используем стиль текста как на скриншоте
                        )
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selected) Color.White else Color(0xFFF0F0F0))
                        .padding(horizontal = 0.dp, vertical = 8.dp) // Внутренние отступы внутри вкладки
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Карта с маркерами
        Box(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEDE7F6))
        ) {
            BirthRateMapView(selectedTab)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Легенда
        Legend(
            items = listOf(
                LegendItemData(
                    Color(0xFFD32F2F),
                    "Высокая рождаемость",
                    "100 детей на 1000 человек"
                ),
                LegendItemData(
                    Color(0xFFFF7043),
                    "Средняя рождаемость",
                    "50 детей на 1000 человек"
                ),
                LegendItemData(
                    Color(0xFFFFCCBC),
                    "Низкая рождаемость",
                    "Меньше 25 детей на 1000 человек"
                ),
            )
        )
    }
}

@Composable
fun Legend(items: List<LegendItemData>) {
    Column {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(item.color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(item.label, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

data class LegendItemData(val color: Color, val label: String, val description: String)

@SuppressLint("ClickableViewAccessibility")
@Composable
fun BirthRateMapView(selectedTab: Int) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(12.0)
                controller.setCenter(GeoPoint(42.8746, 74.5698))

                overlays.clear()

                val circlesData = listOf(
                    CircleData(
                        center = GeoPoint(42.8746, 74.5698),
                        radiusMeters = 3000.0,
                        color = AndroidColor.argb(90, 211, 47, 47)
                    ),
                    CircleData(
                        center = GeoPoint(42.8746, 74.5698),
                        radiusMeters = 2000.0,
                        color = AndroidColor.argb(90, 255, 112, 67)
                    ),
                    CircleData(
                        center = GeoPoint(42.8746, 74.5698),
                        radiusMeters = 1000.0,
                        color = AndroidColor.argb(90, 255, 204, 188)
                    )
                )

                circlesData.forEach { circleData ->
                    val polygon = createCirclePolygon(circleData.center, circleData.radiusMeters)
                    polygon.fillColor = circleData.color
                    polygon.strokeColor = AndroidColor.BLACK
                    polygon.strokeWidth = 2f
                    overlays.add(polygon)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

// Функция для создания многоугольника — приближение круга
fun createCirclePolygon(center: GeoPoint, radiusMeters: Double, pointsCount: Int = 64): org.osmdroid.views.overlay.Polygon {
    val polygon = org.osmdroid.views.overlay.Polygon()
    val points = mutableListOf<GeoPoint>()

    for (i in 0 until pointsCount) {
        val angle = i * 2.0 * Math.PI / pointsCount
        val dx = radiusMeters * Math.cos(angle)
        val dy = radiusMeters * Math.sin(angle)
        points.add(computeOffset(center, dx, dy))
    }

    polygon.points = points
    return polygon
}

// Функция смещения точки в метрах по широте и долготе
fun computeOffset(center: GeoPoint, dx: Double, dy: Double): GeoPoint {
    val earthRadius = 6378137.0 // метров
    val dLat = dy / earthRadius
    val dLon = dx / (earthRadius * Math.cos(Math.toRadians(center.latitude)))

    val lat = center.latitude + Math.toDegrees(dLat)
    val lon = center.longitude + Math.toDegrees(dLon)

    return GeoPoint(lat, lon)
}

data class CircleData(
    val center: GeoPoint,
    val radiusMeters: Double,
    val color: Int
)

