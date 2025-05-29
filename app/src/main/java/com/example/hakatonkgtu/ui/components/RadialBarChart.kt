package com.example.hakatonkgtu.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hakatonkgtu.ui.theme.BlueG
import com.example.hakatonkgtu.ui.theme.GreenG
import com.example.hakatonkgtu.ui.theme.OrangeG
import com.example.hakatonkgtu.ui.theme.PinkG
import com.example.hakatonkgtu.ui.theme.PurpleG
import com.example.hakatonkgtu.ui.theme.YellowG

data class AgeGroup(
    val label: String,
    val count: Int,
    val description: String,
    val color: Color
)

val ageGroups = listOf(
    AgeGroup("Дети 0-1 год", 150000, "Самая младшая группа", PurpleG),
    AgeGroup("Дети 1-3 года", 180000, "Наблюдается небольшой рост", OrangeG),
    AgeGroup("Дети 3-6 лет", 220000, "Основная целевая группа для садов", YellowG),
    AgeGroup("Дети 7-10 лет", 350000, "Начальная школа", GreenG),
    AgeGroup("Дети 11-14 лет", 400000, "Средняя школа", BlueG),
    AgeGroup("Дети 15-17 лет", 450000, "Старшая школа", PinkG),
)

@Composable
fun RadialBarChart() {
    val total = ageGroups.sumOf { it.count }
    val strokeWidth = 30f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Центрируем все дочерние элементы
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(200.dp)) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    val gapAngle = 2f // угол отступа в градусах

                    var startAngle = -90f // старт с верхней точки
                    val total = ageGroups.sumOf { it.count }
                    val gapTotal = gapAngle * ageGroups.size
                    val adjustedTotalAngle = 360f - gapTotal

                    ageGroups.forEach { group ->
                        val sweepAngle = group.count / total.toFloat() * adjustedTotalAngle
                        drawArc(
                            color = group.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        startAngle += sweepAngle + gapAngle
                    }

                }
                // Центр с общей суммой
                Text(
                    text = "%,d".format(total),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Легенда
            ageGroups.forEach { group ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(color = group.color, shape = CircleShape)
                    )
                    Column {
                        Text(text = group.label, fontWeight = FontWeight.SemiBold)
                        Text(text = group.description, fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "%,d".format(group.count))
                }
            }
        }
    }
}
