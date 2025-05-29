package com.example.hakatonkgtu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hakatonkgtu.ui.components.BirthRateTrendChart
import com.example.hakatonkgtu.ui.components.EducationBudgetCard
import com.example.hakatonkgtu.ui.components.NewsAndUpdatesCard
import com.example.hakatonkgtu.ui.components.Spa
import com.example.hakatonkgtu.ui.theme.DropGray

@Composable
fun InteractiveMapContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DropGray)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Интерактивная карта", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spa(6.dp)
            Text(
                "Визуализация демографических данных и прогнозов по регионам и возрастным группам",
                fontSize = 14.sp,
                color = Color(0xCC101323)
            )
        }
        Spa(16.dp)

        // --- Карта ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(660.dp)
                .background(Color.White, RoundedCornerShape(12.dp)) // Заглушка для карты
        ) {
            BirthRateMapScreen()
        }
        Spa(24.dp)

        // --- График тренда рождаемости ---
        BirthRateTrendChart()
        Spa(16.dp)

        // --- Бюджет на образование ---
        EducationBudgetCard()
        Spa(16.dp)

        // --- Новости и обновления ---
        NewsAndUpdatesCard()
    }
}
