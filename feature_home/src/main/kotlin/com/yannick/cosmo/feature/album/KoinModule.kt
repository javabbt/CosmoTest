package com.yannick.cosmo.feature.album

import com.yannick.cosmo.feature.album.data.dataModule
import com.yannick.cosmo.feature.album.domain.domainModules
import com.yannick.cosmo.feature.album.presentation.presentationModule

val featureHomeModules = listOf(
    presentationModule,
    dataModule,
    domainModules
)
