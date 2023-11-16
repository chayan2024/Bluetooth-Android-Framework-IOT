package com.withjetpack.bluetooth_android_framework_iot.navhost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.withjetpack.bluetooth_android_framework_iot.presentation.detail.DetailScreen
import com.withjetpack.bluetooth_android_framework_iot.presentation.main.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("detailScreen") {
            DetailScreen(navController)
        }
    }
}


