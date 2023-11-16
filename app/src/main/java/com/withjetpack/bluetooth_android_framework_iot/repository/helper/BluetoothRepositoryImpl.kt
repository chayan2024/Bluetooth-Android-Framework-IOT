package com.withjetpack.bluetooth_android_framework_iot.repository.helper

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.withjetpack.bluetooth_android_framework_iot.repository.BluetoothRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.util.*
import javax.inject.Inject

class BluetoothRepositoryImpl @Inject constructor(
    private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter
) : BluetoothRepository {

    private var bluetoothSocket: BluetoothSocket? = null
    private var connectedJob: Job? = null

    private var deviceDiscoveryReceiver: BroadcastReceiver? = null
    private val _availabledeviceList = MutableStateFlow<List<BluetoothDevice>>(emptyList())
    private val _paireddeviceList = MutableStateFlow<List<BluetoothDevice>>(emptyList())

    @SuppressLint("MissingPermission")
    override fun startDiscovery(): MutableStateFlow<List<BluetoothDevice>> {

        deviceDiscoveryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (BluetoothDevice.ACTION_FOUND == intent?.action) {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)!!

                    if (context?.let {
                            ActivityCompat.checkSelfPermission(
                                it, Manifest.permission.BLUETOOTH_CONNECT
                            )
                        } != PackageManager.PERMISSION_GRANTED) {
                        return
                    }
                    device?.let {
                        // Check if the device is not already in the list
                        if (!_availabledeviceList.value.contains(it)) {
                            val newList = _availabledeviceList.value.toMutableList().apply { add(it) }
                            _availabledeviceList.value = newList
                        }
                    }
                }
            }
        }

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(deviceDiscoveryReceiver, filter)
        bluetoothAdapter?.startDiscovery()

        return _availabledeviceList;
    }

    @SuppressLint("MissingPermission")
    override fun getPairedDevice(): StateFlow<List<BluetoothDevice>> {

        if (bluetoothAdapter != null) {
            // Ensure Bluetooth is enabled
            if (!bluetoothAdapter.isEnabled) {
                // You might want to prompt the user to enable Bluetooth
            }
            // Get the list of paired devices
            val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
            _paireddeviceList.value= pairedDevices.toList()
        } else {
            // Bluetooth is not supported on this device
        }
        return _paireddeviceList
    }

    override fun stopDiscovery() {
        TODO("Not yet implemented")
    }

    @SuppressLint("MissingPermission")
    override suspend fun connectToDevice(device: BluetoothDevice) {

        try {
            if (device.bondState != BluetoothDevice.BOND_BONDED) {
                // Pairing is required
                val pin = "1234" // You might generate or retrieve the PIN dynamically
                device.setPin(pin.toByteArray())
                device.createBond()
            }

            withContext(Dispatchers.IO) {
                bluetoothSocket?.close() // Close existing socket if any
                bluetoothSocket = createBluetoothSocket(device)
                bluetoothSocket?.connect()
            }

            connectedJob?.cancel() // Cancel any existing job
        } catch (e: Exception) {
            // Handle exceptions
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun sendDataToDevice(device: BluetoothDevice) {
        val socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))
        socket.connect()
        socket.use {
            it.connect()
            val outputStream: OutputStream = it.outputStream
            val dataToSend = "Hello, World!".toByteArray()
            outputStream.write(dataToSend)
        }
//        try {
//            val outputStream: OutputStream = socket.outputStream
//            val dataToSend = "Hello, World!".toByteArray()
//            outputStream.write(dataToSend)
//        } catch (e: IOException) {
//            // Handle exceptions
//        } finally {
//            try {
//                socket.close()
//            } catch (e: IOException) {
//                // Handle close exceptions
//            }
//        }
    }

    @SuppressLint("MissingPermission")
    private fun createBluetoothSocket(device: BluetoothDevice): BluetoothSocket {
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // SPP UUID
        return device.createRfcommSocketToServiceRecord(uuid)
    }
}