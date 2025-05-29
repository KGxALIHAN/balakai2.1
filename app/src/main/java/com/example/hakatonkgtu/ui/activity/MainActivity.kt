package com.example.hakatonkgtu.ui.activity

import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hakatonkgtu.ui.navigation.AppNavHost
import com.example.hakatonkgtu.ui.theme.HakatonKGTUTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Configuration.getInstance().load(applicationContext, getDefaultSharedPreferences(applicationContext))
        setContent {
            HakatonKGTUTheme {
                AppNavHost()
            }
        }
    }
}
