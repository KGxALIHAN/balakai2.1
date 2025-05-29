package com.example.hakatonkgtu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hakatonkgtu.ui.screens.MainScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val startDestination = Route.MAIN

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Route.MAIN){ MainScreen(navController) }
    }
}


object Route {
    const val MAIN = "main"
}
