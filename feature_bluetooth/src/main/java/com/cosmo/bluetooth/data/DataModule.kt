package com.cosmo.bluetooth.data

import com.cosmo.bluetooth.data.bluetooth.AndroidBluetoothController
import com.cosmo.bluetooth.domain.bluetooth.BluetoothController
import org.koin.dsl.module

internal val dataModule = module {
    single<BluetoothController> {
        AndroidBluetoothController(get())
    }
}
