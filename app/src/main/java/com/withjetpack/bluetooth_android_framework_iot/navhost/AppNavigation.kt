package com.withjetpack.bluetooth_android_framework_iot.navhost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.withjetpack.bluetooth_android_framework_iot.navhost.util.Screen
import com.withjetpack.bluetooth_android_framework_iot.presentation.detail.DetailScreen
import com.withjetpack.bluetooth_android_framework_iot.presentation.main.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(Screen.DetailScreen.route) {
            DetailScreen(navController)
        }
    }
}


