package com.withjetpack.bluetooth_android_framework_iot.common
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.withjetpack.bluetooth_android_framework_iot.ui.theme.BluetoothAndroidFrameworkIOTTheme

@Composable
fun ToolBar() {

    var toolbarTitle by remember { mutableStateOf("Bluetooth Framework") }

    BluetoothAndroidFrameworkIOTTheme {

        Surface(
            color = MaterialTheme.colors.secondary, modifier = Modifier.height(56.dp)
        ) {
            Column {

                TopAppBar(title = { Text(text = toolbarTitle) },
                    backgroundColor = Color(0xFF6200EE),
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu, // Replace with your menu icon
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        // Actions (e.g., buttons or icons)
                    }

                )

            }
        }

    }
}