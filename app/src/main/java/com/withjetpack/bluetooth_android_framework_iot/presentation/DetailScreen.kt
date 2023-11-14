package com.withjetpack.bluetooth_android_framework_iot.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.withjetpack.bluetooth_android_framework_iot.common.BluetoothAvailableDeviceCard
import com.withjetpack.bluetooth_android_framework_iot.common.BluetoothPairedDeviceCard
import com.withjetpack.bluetooth_android_framework_iot.common.ToolBar
import com.withjetpack.bluetooth_android_framework_iot.presentation.detail.BluetoothViewModel

@Composable
fun DetailScreen(navController: NavHostController) {

    val viewModel: BluetoothViewModel = hiltViewModel()

    LaunchedEffect(true) {
        viewModel.startBluetoothDiscovery()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        ToolBar()

        val availDeviceList by viewModel.availabledeviceList.collectAsState()
        val pairedDeviceList by viewModel.paireddeviceList.collectAsState()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            elevation = 8.dp,
        ) {
            Text(
                text = "Available Device",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp, 16.dp, 16.dp, 16.dp)
            )
        }

        LazyColumn {
            items(availDeviceList) { deviceName ->
                if (ActivityCompat.checkSelfPermission(
                        LocalContext.current, Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }

                BluetoothAvailableDeviceCard(viewModel, deviceName, false) {}
            }

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            elevation = 8.dp,
        ) {
            Text(
                text = "Paired Device",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp, 16.dp, 16.dp, 16.dp)
            )
        }

        LazyColumn {
            items(pairedDeviceList) { deviceName ->
                if (ActivityCompat.checkSelfPermission(
                        LocalContext.current, Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }

                BluetoothPairedDeviceCard(viewModel, deviceName, false) {}
            }

        }

    }
}
