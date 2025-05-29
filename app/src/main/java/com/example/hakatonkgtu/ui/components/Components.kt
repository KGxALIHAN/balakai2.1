package com.example.hakatonkgtu.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.hakatonkgtu.R
import com.example.hakatonkgtu.ui.theme.DropGray

@Composable
fun Spa(height: Dp = 20.dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun CustomYearDropdown() {
    val years = listOf("2022 г", "2023 г", "2024 г", "2025 г")
    var expanded by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf(years[2]) }

    Box(modifier = Modifier.wrapContentSize()) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)) // гладкие углы
                .clickable { expanded = true }
                .background(DropGray) // светлый фон
                .padding(horizontal = 12.dp, vertical = 8.dp), // размер
            color = Color.Transparent
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedYear,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color.Black
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .animateContentSize() // плавное раскрытие
        ) {
            years.forEach { year ->
                DropdownMenuItem(
                    text = {
                        Text(
                            year,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W800
                        )
                    },
                    onClick = {
                        selectedYear = year
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TopTabs(selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Обзорная панель", "Интерактивная карта")
    val icons = listOf(R.drawable.ic_panel, R.drawable.ic_document_search)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Start
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedIndex == index

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF2F80ED) else Color(0xFFEFF3FA),
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Black,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            val fontWeight = animateFloatAsState(
                targetValue = if (isSelected) 700f else 400f,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20)) // Мягкие пилюли
                    .background(backgroundColor)
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(icons[index]),
                        contentDescription = title,
                        tint = contentColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = title,
                        color = contentColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(fontWeight.value.toInt())
                    )
                }
            }
        }
    }
}

@Composable
fun TimeTabs(selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Сейчас", "Через год", "Через 5 лет", "Через 10 лет")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F6FA))
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(tabs) { index, title ->
            val isSelected = index == selectedIndex

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Transparent,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF2D2D2D) else Color(0xFF888888),
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            val fontWeight by animateFloatAsState(
                targetValue = if (isSelected) 700f else 400f,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(backgroundColor)
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = textColor,
                    fontWeight = FontWeight(fontWeight.toInt())
                )
            }
        }
    }
}


@Composable
fun InfoWithTooltip() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        Icon(
            painter = painterResource(R.drawable.ic_info),
            contentDescription = "Информация",
            modifier = Modifier
                .size(24.dp)
                .clickable { expanded = !expanded }
        )

        if (expanded) {
            Popup(
                alignment = Alignment.TopCenter,
                offset = IntOffset(0, -80),
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    shadowElevation = 8.dp,
                    color = Color.Black.copy(alpha = 0.85f),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 200.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Данный прогноз был рассчитан исходя из данных о рождаемости и вместимости школ",
                        color = Color.White,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
