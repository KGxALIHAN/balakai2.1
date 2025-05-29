package com.example.hakatonkgtu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hakatonkgtu.R

@Composable
fun PlacesForecast() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FA)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Прогноз потребности в местах",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                InfoWithTooltip()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Сколько учебных мест требуется",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Первый блок
            PlaceRequirementBlock(
                places = "30,000 мест",
                title = "Требуется через год",
                description = "Растёт за счёт миграции и рождаемости"
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFFE1E1E1)
            )

            // Второй блок
            PlaceRequirementBlock(
                places = "75,000 мест",
                title = "Требуется через 5 лет",
                description = "Рост нагрузки в регионах с миграцией"
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFFE1E1E1)
            )

            // Третий блок
            PlaceRequirementBlock(
                places = "120,000 мест",
                title = "Требуется через 10 лет",
                description = "Требуется расширение инфраструктуры"
            )
        }
    }
}

@Composable
fun PlaceRequirementBlock(
    places: String,
    title: String,
    description: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color(0xFFFFE0B2), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = places,
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        GradientCircleIcon(
            iconPainter = painterResource(R.drawable.ic_lampochks),
            contentDescription = "Idea"
        )
    }
}


@Composable
fun GradientCircleIcon(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    contentDescription: String?,
    gradientColors: List<Color> = listOf(Color(0xFFFF7043), Color(0xFFFFAB91)),
    iconTint: Color = Color.White,
    circleSize: Dp = 40.dp // размер круга
) {
    Box(
        modifier = modifier
            .size(circleSize)
            .background(
                brush = Brush.linearGradient(colors = gradientColors),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = iconTint,
            modifier = Modifier.size(circleSize * 0.5f) // иконка поменьше круга
        )
    }
}
