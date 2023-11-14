package com.withjetpack.bluetooth_android_framework_iot.common

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.withjetpack.bluetooth_android_framework_iot.presentation.detail.BluetoothViewModel

@SuppressLint("MissingPermission")
@Composable
fun BluetoothPairedDeviceCard(
    viewModel: BluetoothViewModel,
    bluetoothDevice: BluetoothDevice,
    isSelected: Boolean,
    onClick: (BluetoothDevice) -> Unit
) {

    val gradientColors = listOf(Color(0xFF009688), Color(0xFF004D40))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 5.dp, 16.dp, 16.dp)
            .background(if (isSelected) Color.Gray else Color.Transparent)
            .clickable {
                viewModel.sendDataToDevice(device = bluetoothDevice)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Text(
                text = if (bluetoothDevice.name.isNullOrEmpty()) "" else bluetoothDevice.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Light,
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 8.dp)
            )
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = if (bluetoothDevice.address.isNullOrEmpty())"" else bluetoothDevice.address,
                style = MaterialTheme.typography.body2,
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 30.dp)
            )
        }
    }
}