package com.example.hakatonkgtu.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hakatonkgtu.R
import com.example.hakatonkgtu.ui.components.CustomYearDropdown
import com.example.hakatonkgtu.ui.components.InfoWithTooltip
import com.example.hakatonkgtu.ui.components.RadialBarChart
import com.example.hakatonkgtu.ui.components.Spa
import com.example.hakatonkgtu.ui.components.TimeTabs
import com.example.hakatonkgtu.ui.components.TopTabs
import com.example.hakatonkgtu.ui.theme.DropGray
import com.example.hakatonkgtu.ui.theme.TextGray


@Composable
fun MainScreen(navController: NavController) {

    var topTabIndex by remember { mutableStateOf(1) }
    var timeTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .statusBarsPadding()
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_balakai),
                        contentDescription = null
                    )
                    CustomYearDropdown()
                }
                Spa(30.dp)
                TopTabs(selectedIndex = topTabIndex) { topTabIndex = it }
                Spa(12.dp)
                TimeTabs(selectedIndex = timeTabIndex) { timeTabIndex = it }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                if(topTabIndex == 0) OverviewContent() else InteractiveMapContent()
            }
        }
    )

}

