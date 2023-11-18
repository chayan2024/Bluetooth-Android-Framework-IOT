package com.withjetpack.bluetooth_android_framework_iot.presentation.detail

import android.bluetooth.BluetoothDevice
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.withjetpack.bluetooth_android_framework_iot.presentation.detail.viewmodel.BluetoothViewModel
import com.withjetpack.bluetooth_android_framework_iot.repository.BluetoothRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BluetoothViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var bluetoothRepository: BluetoothRepository

    private lateinit var viewModel: BluetoothViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = BluetoothViewModel(bluetoothRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `startBluetoothDiscovery updates available and paired device lists`() =
        runBlockingTest {
            val mockAvailableDevices = listOf(mock(BluetoothDevice::class.java))
            val mockPairedDevices = listOf(mock(BluetoothDevice::class.java))
            whenever(bluetoothRepository.startDiscovery()).thenReturn(
                MutableStateFlow(mockAvailableDevices)
            )
            whenever(bluetoothRepository.getPairedDevice()).thenReturn(
                MutableStateFlow(mockPairedDevices)
            )
            viewModel.startBluetoothDiscovery()
            assertEquals(mockAvailableDevices, viewModel.availabledeviceList.value)
            assertEquals(mockPairedDevices, viewModel.paireddeviceList.value)
        }

}
