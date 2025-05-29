package com.example.hakatonkgtu.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hakatonkgtu.R
import com.example.hakatonkgtu.ui.components.InfoWithTooltip
import com.example.hakatonkgtu.ui.components.PlacesForecast
import com.example.hakatonkgtu.ui.components.RadialBarChart
import com.example.hakatonkgtu.ui.components.Spa
import com.example.hakatonkgtu.ui.theme.DropGray
import com.example.hakatonkgtu.ui.theme.TextGray

@Composable
fun OverviewContent() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(DropGray)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Обзорная панель", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spa(6.dp)
        Text(
            "Обзор ключевых показателей и текущей ситуации по учебным заведениям",
            fontSize = 14.sp, color = Color(0xCC101323)
        )
    }
    Spa(16.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text("Число детей", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spa(4.dp)
            Text("Данные переписи населения 2024г", color = TextGray, fontSize = 14.sp)
        }
        InfoWithTooltip()
    }
    Spa(10.dp)
    RadialBarChart()
    Spa(10.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Потребности в учебных местах",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                InfoWithTooltip()
            }
            Text(
                text = "Данные переписи населения 2024г",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Левая часть - Школы
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.ic_book),
                        contentDescription = "Вместимость школ",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Вместимость школ",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "1,100,000",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                // Вертикальная линия-разделитель
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .width(1.dp)
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
                // Правая часть - Детсады
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.ic_child),
                        contentDescription = "Вместимость детсадов",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Вместимость детсадов",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "450,000",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
    Spa(10.dp)
    PlacesForecast()
    Spa()
    SchoolMapScreen()
}