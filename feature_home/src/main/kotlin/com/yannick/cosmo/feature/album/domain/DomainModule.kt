package com.yannick.cosmo.feature.album.domain

import com.yannick.cosmo.feature.album.domain.usecases.GetDevicesUseCase
import org.koin.dsl.module

internal val domainModules = module {
   single {
       GetDevicesUseCase(get())
   }
}
