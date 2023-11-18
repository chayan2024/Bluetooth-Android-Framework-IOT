package com.withjetpack.bluetooth_android_framework_iot.repository.helper

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.withjetpack.bluetooth_android_framework_iot.repository.BluetoothRepository
import io.mockk.every
import io.mockk.spyk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BluetoothRepositoryImplTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var bluetoothRepository: BluetoothRepositoryImpl

    @Before
    fun setUp() {

        bluetoothRepository =  spyk(BluetoothRepositoryImpl(context, bluetoothAdapter))
    }

    @Test
    fun `startDiscovery registers receiver and updates available device list`() {
        val mockAvailableDevices = listOf(mock(BluetoothDevice::class.java))
        whenever(bluetoothAdapter.isEnabled).thenReturn(true)
        every { bluetoothRepository.startDiscovery() } returns MutableStateFlow(mockAvailableDevices)
        val result = bluetoothRepository.startDiscovery()
        assertEquals(mockAvailableDevices, result.value)
    }

}
