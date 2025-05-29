package com.example.hakatonkgtu.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsAndUpdatesCard() {
    val newsItems = listOf(
        NewsItem(
            title = "Обновлены данные рождаемости и миграции за 2024 год",
            date = "27.05.2025",
            source = "kaktus.media"
        ),
        NewsItem(
            title = "Изменены стандарты площади на одно учебное место (увеличение на 5%)",
            date = "20.05.2025",
            source = "azattyk.kg"
        ),
        NewsItem(
            title = "Запущен пилотный проект по цифровизации учета в школах Чуйской области",
            date = "15.05.2025",
            source = "sputnik.kg"
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Новости и обновления",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Здесь собраны последние новости и обновления",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                InfoWithTooltip()
            }

            Spacer(modifier = Modifier.height(16.dp))

            newsItems.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: открыть ссылку */ }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = item.title,
                        fontSize = 14.sp,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = item.date,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item.source,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = "Открыть",
                            tint = Color(0xFF2C7BE5),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                if (index != newsItems.lastIndex) {
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }
        }
    }
}

data class NewsItem(
    val title: String,
    val date: String,
    val source: String
)




@Composable
fun EducationBudgetCard() {
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // Заголовок с иконкой
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Бюджет на образование",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Данные от Министерства финансов",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                InfoWithTooltip()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Диаграмма
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 20.dp.toPx()
                    val gapBetweenArcs = 8.dp.toPx() // Отступ между дугами

                    val padding = strokeWidth / 2

                    // Внешний полукруг (синий)
                    drawArc(
                        color = Color(0xFF11A9FF),
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(padding, padding),
                        size = Size(size.width - strokeWidth, size.height - strokeWidth),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Square)
                    )

                    // Внутренний полукруг (красный) с отступом (gap)
                    val innerPadding = padding + strokeWidth + gapBetweenArcs
                    val innerSize = Size(
                        size.width - 2 * innerPadding,
                        size.height - 2 * innerPadding
                    )

                    drawArc(
                        color = Color(0xFFE65151),
                        startAngle = 180f,
                        sweepAngle = 60f,
                        useCenter = false,
                        topLeft = Offset(innerPadding, innerPadding),
                        size = innerSize,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Square)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Легенда
            Column {
                LegendItem(
                    color = Color(0xFF11A9FF),
                    label = "Бюджет на образование",
                    value = "18 млрд KGS"
                )
                Spacer(modifier = Modifier.height(12.dp))
                LegendItem(
                    color = Color(0xFFE65151),
                    label = "Расходы на строительство новых учебных заведений",
                    value = "5 млрд KGS"
                )
            }
        }
    }
}


@Composable
fun LegendItem(color: Color, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}


@Composable
fun BirthRateTrendChart() {
    val data = listOf(
        "2020" to 52000,
        "2021" to 53500,
        "2022" to 54000,
        "2023" to 55500,
        "2024" to 57000,
        "2025" to 58500,
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "График тренда рождаемости (2020-2025)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                InfoWithTooltip()
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Данные переписи населения 2020-2025", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            data.forEach { (year, count) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(year)
                    Spacer(modifier = Modifier.width(8.dp))
                    val progress = count / 60000f // максимум для масштаба
                    LinearProgressIndicator(
                        progress = progress,
                        color = Color(0xFF2E90FA),
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .clip(MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(String.format("%,d", count))
                }
            }
        }
    }

}