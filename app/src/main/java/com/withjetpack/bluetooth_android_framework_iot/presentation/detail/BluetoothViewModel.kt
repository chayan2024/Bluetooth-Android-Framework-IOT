package com.withjetpack.bluetooth_android_framework_iot.presentation.detail

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.withjetpack.bluetooth_android_framework_iot.repository.BluetoothRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) : ViewModel() {

    private val _availabledeviceList = MutableStateFlow<List<BluetoothDevice>>(emptyList())
    val availabledeviceList: StateFlow<List<BluetoothDevice>> = _availabledeviceList
    val _paireddeviceList= MutableStateFlow<List<BluetoothDevice>>(emptyList())
    val paireddeviceList: StateFlow<List<BluetoothDevice>> = _paireddeviceList

    fun startBluetoothDiscovery() {
        viewModelScope.launch {
            val discoveredDevices = bluetoothRepository.startDiscovery().value
            _availabledeviceList.value = discoveredDevices
            val pairedDevices = bluetoothRepository.getPairedDevice().value
            _paireddeviceList.value = pairedDevices
        }
    }

    fun stopBluetoothDiscovery() {
        bluetoothRepository.stopDiscovery()
    }

    fun connectToDevice(device: BluetoothDevice) {
        viewModelScope.launch {
            bluetoothRepository.connectToDevice(device)
        }
    }
    fun sendDataToDevice(device: BluetoothDevice) {
        viewModelScope.launch {
            bluetoothRepository.connectToDevice(device)
        }
    }
}

