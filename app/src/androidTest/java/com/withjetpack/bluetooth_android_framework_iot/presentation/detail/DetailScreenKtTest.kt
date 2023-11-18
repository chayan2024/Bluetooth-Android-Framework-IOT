package com.withjetpack.bluetooth_android_framework_iot.presentation.detail

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.withjetpack.bluetooth_android_framework_iot.MainActivity
import com.withjetpack.bluetooth_android_framework_iot.di.BluetoothModuleTest
import com.withjetpack.bluetooth_android_framework_iot.navhost.util.Screen
import com.withjetpack.bluetooth_android_framework_iot.ui.theme.BluetoothAndroidFrameworkIOTTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(BluetoothModuleTest::class)
class DetailScreenKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            BluetoothAndroidFrameworkIOTTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.DetailScreen.route
                ) {
                    composable(route = Screen.DetailScreen.route) {
                        DetailScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun verifyAvailableDeviceCardDisplayed() {
        composeRule.onNodeWithText("Available Device").assertIsDisplayed()
        val availDeviceList = listOf("Device1", "Device2")
        availDeviceList.forEach { deviceName ->
            composeRule.onNodeWithText(deviceName).assertIsDisplayed()
        }
    }

    @Test
    fun verifyPairedDeviceCardDisplayed() {
        composeRule.onNodeWithText("Paired Device").assertIsDisplayed()
        val pairedDeviceList = listOf("Device1", "Device2")
        pairedDeviceList.forEach { deviceName ->
            composeRule.onNodeWithText(deviceName).assertIsDisplayed()
        }
    }

}