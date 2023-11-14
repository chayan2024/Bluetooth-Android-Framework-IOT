package com.withjetpack.bluetooth_android_framework_iot.repository

import android.bluetooth.BluetoothDevice
import kotlinx.coroutines.flow.StateFlow

interface BluetoothRepository {
    fun startDiscovery(): StateFlow<List<BluetoothDevice>>
    fun getPairedDevice(): StateFlow<List<BluetoothDevice>>
    fun stopDiscovery()
    suspend fun connectToDevice(device: BluetoothDevice)
    suspend fun sendDataToDevice(device: BluetoothDevice)
}