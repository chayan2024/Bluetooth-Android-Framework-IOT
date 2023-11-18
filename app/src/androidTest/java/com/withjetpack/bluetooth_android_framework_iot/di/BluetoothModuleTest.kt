package com.withjetpack.bluetooth_android_framework_iot.di

import android.bluetooth.BluetoothAdapter
import android.content.Context
import com.withjetpack.bluetooth_android_framework_iot.repository.BluetoothRepository
import com.withjetpack.bluetooth_android_framework_iot.repository.helper.BluetoothRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BluetoothModuleTest {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideBluetoothAdapter(): BluetoothAdapter {
        return BluetoothAdapter.getDefaultAdapter() ?: throw IllegalStateException("Bluetooth not supported on this device.")
    }


    @Provides
    @Singleton
    fun provideBluetoothRepository(
        context: Context,
        bluetoothAdapter: BluetoothAdapter
    ): BluetoothRepository {
        return BluetoothRepositoryImpl(context, bluetoothAdapter)
    }
}
