package com.withjetpack.bluetooth_android_framework_iot.presentation.main
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.matcher.ViewMatchers.*
import com.withjetpack.bluetooth_android_framework_iot.MainActivity
import com.withjetpack.bluetooth_android_framework_iot.common.CustomSwitch
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
class MainScreenKtTest {

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
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun enabledSwitchTest() {
        composeRule.setContent {
            CustomSwitch(
                switchState = false,
                onCheckedChange = {  },
                labelText = "Please Turn on the Bluetooth Permission"
            )
        }

        composeRule.onNodeWithText("Please Turn on the Bluetooth Permission")
            .assertIsDisplayed()
            .performClick()

    }

    @Test
    fun clickButton_navigatesToDetailScreen() {
        composeRule.setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.MainScreen.route
            ) {
                composable(Screen.MainScreen.route) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.DetailScreen.route)
                            assertTrue(navController.currentDestination?.route == Screen.DetailScreen.route)
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                            .padding(20.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Search Device")
                    }
                }

                composable(Screen.DetailScreen.route) {
                }
            }
        }
        composeRule.onNodeWithText("Search Device").performClick()
    }

}