package com.example.hakatonkgtu.ui.screens

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hakatonkgtu.R
import com.example.hakatonkgtu.data.model.District
import com.example.hakatonkgtu.data.model.EducationalInstitution
import com.example.hakatonkgtu.data.model.GeoPointData
import com.example.hakatonkgtu.data.model.Kindergartenn
import com.example.hakatonkgtu.data.model.MapDataResponse
import com.example.hakatonkgtu.data.model.Schooll
import com.example.hakatonkgtu.ui.components.CustomInfoWindow
import com.example.hakatonkgtu.ui.components.InfoWithTooltip
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun SchoolMapScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Школы", "Детские сады")
    val coroutineScope = rememberCoroutineScope()
    var schoolls by remember { mutableStateOf<List<Schooll>>(emptyList()) }
    var kindergartenns by remember { mutableStateOf<List<Kindergartenn>>(emptyList()) }

    // Загрузка данных из API
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = MapDataResponse(
                districts = listOf(
                    District(
                        id = 1,
                        name = "Leninsky",
                        geometry = """{"type": "Polygon", "coordinates": [[[74.55, 42.85], [74.60, 42.85], [74.60, 42.90], [74.55, 42.90], [74.55, 42.85]]]}"""
                    )
                    // Добавьте другие районы, если нужно
                ),
                schoolls = listOf(
                    Schooll(
                        id = 1,
                        name = "School 1 Leninsky",
                        location = """{"type": "Point", "coordinates": [74.57, 42.87]}""",
                        capacity = 1200,
                        current_load = 1000
                    )
                    // Добавьте другие школы, если нужно
                ),
                kindergartenns = listOf(
                    Kindergartenn(
                        id = 1,
                        name = "Kindergarten 1 Leninsky",
                        location = """{"type": "Point", "coordinates": [74.56, 42.86]}""",
                        capacity = 300,
                        current_load = 250
                    )
                    // Добавьте другие детские сады, если нужно
                )
            )
            //val response = RetrofitClient.apiService.getMapData()

            response.let { mapData ->
                schoolls = mapData.schoolls
                kindergartenns = mapData.kindergartenns

            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text(
                text = "Потребности в учебных местах",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            InfoWithTooltip()
        }
        Text(
            text = "Данные о загруженности школ и детских садов",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color(0xFFF0F0F0),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color.Black,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTab == index) Color.Black else Color.DarkGray
                        )
                    },
                    modifier = Modifier.background(
                        if (selectedTab == index) Color.White else Color(0xFFF0F0F0),
                        RoundedCornerShape(20.dp)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .height(420.dp) // Этот параметр конфликтует с новым размером
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            SchoolMapView(
                schoolls = schoolls,
                kindergartenns = kindergartenns,
                showSchools = selectedTab == 0
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Legend
        LegendItem(color = Color.Red, label = "Перегружено", description = "Нехватка мест")
        Spacer(modifier = Modifier.height(8.dp))
        LegendItem(color = Color(0xFFFFA500), label = "Заполнено", description = "Мест нет")
        Spacer(modifier = Modifier.height(8.dp))
        LegendItem(color = Color(0xFF4CAF50), label = "Сбалансировано", description = "Есть места")
    }
}

@Composable
fun LegendItem(color: Color, label: String, description: String) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(color, RoundedCornerShape(7.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
@Composable
fun SchoolMapView(schoolls: List<Schooll>, kindergartenns: List<Kindergartenn>, showSchools: Boolean) {
    val context = LocalContext.current

    // Вспомогательная функция создания маркера
    fun createMarker(
        mapView: MapView,
        lat: Double,
        lon: Double,
        title: String,
        snippet: String,
        color: String,
        context: android.content.Context
    ): Marker {
        return Marker(mapView).apply {
            position = GeoPoint(lat, lon)
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            this.title = title
            this.snippet = snippet
            icon = context.getDrawable(
                when (color) {
                    "Red" -> R.drawable.ic_red_school_marker
                    "Orange" -> R.drawable.ic_orange_school_marker
                    else -> R.drawable.ic_green_school_marker
                }
            )
            mapView.overlays
                .filterIsInstance<Marker>()
                .forEach { it.closeInfoWindow() }


            infoWindow = CustomInfoWindow(mapView)
        }
    }

    // Инициализация OSMDroid
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))
    }

    AndroidView(
        factory = {
            val mapView = MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(11.5)
                controller.setCenter(GeoPoint(42.87, 74.59)) // Центр Бишкека

                val items: List<EducationalInstitution> =
                    if (showSchools) schoolls else kindergartenns
                val markersData = items.map { item ->
                    val geoPoint = Gson().fromJson(item.location, GeoPointData::class.java)
                    val lat = geoPoint.coordinates[1]
                    val lon = geoPoint.coordinates[0]
                    val loadPercent = (item.current_load.toFloat() / item.capacity * 100).toInt()
                    val status = when {
                        loadPercent > 100 -> "Перегружено $loadPercent%"
                        loadPercent == 100 -> "Заполнено"
                        else -> "Сбалансировано"
                    }
                    val color = when {
                        loadPercent > 100 -> "Red"
                        loadPercent == 100 -> "Orange"
                        else -> "Green"
                    }
                    Quadruple(lat, lon, item.name, status, color)
                }

                markersData.forEach { (lat, lon, title, snippet, color) ->
                    val marker = createMarker(this, lat, lon, title, snippet, color, context)
                    overlays.add(marker)
                }
            }
            mapView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    mapView.overlays
                        .filterIsInstance<Marker>()
                        .forEach { it.closeInfoWindow() }
                }
                false
            }

            mapView
        },
        modifier = Modifier
            .width(380.dp)
            .height(600.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray),
        update = { mapView ->
            mapView.overlays.clear()

            val items: List<EducationalInstitution> = if (showSchools) schoolls else kindergartenns
            val markersData = items.map { item ->
                val geoPoint = Gson().fromJson(item.location, GeoPointData::class.java)
                val lat = geoPoint.coordinates[1]
                val lon = geoPoint.coordinates[0]
                val loadPercent = (item.current_load.toFloat() / item.capacity * 100).toInt()
                val status = when {
                    loadPercent > 100 -> "Перегружено $loadPercent%"
                    loadPercent == 100 -> "Заполнено"
                    else -> "Сбалансировано"
                }
                val color = when {
                    loadPercent > 100 -> "Red"
                    loadPercent == 100 -> "Orange"
                    else -> "Green"
                }
                Quadruple(lat, lon, item.name, status, color)
            }

            markersData.forEach { (lat, lon, title, snippet, color) ->
                val marker = createMarker(mapView, lat, lon, title, snippet, color, context)
                mapView.overlays.add(marker)
            }
            mapView.invalidate()
        }
    )
}

data class Quadruple<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
)


