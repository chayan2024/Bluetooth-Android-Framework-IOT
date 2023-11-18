package com.withjetpack.bluetooth_android_framework_iot.navhost.util

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object DetailScreen : Screen("DetailScreen")
}
