package com.yannick.cosmo.feature.album.presentation

import com.yannick.cosmo.feature.album.presentation.screen.devicedetail.ContactDetailScreenViewModel
import com.yannick.cosmo.feature.album.presentation.screen.deviceslist.DevicesScreenScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel {
        DevicesScreenScreenViewModel(get())
    }
    viewModel {
        ContactDetailScreenViewModel(get())
    }
}
