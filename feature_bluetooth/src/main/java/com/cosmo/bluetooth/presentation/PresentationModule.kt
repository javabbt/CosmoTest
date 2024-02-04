package com.cosmo.bluetooth.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel {
        com.cosmo.bluetooth.presentation.bluetooth.BluetoothViewModel(get())
    }
}
