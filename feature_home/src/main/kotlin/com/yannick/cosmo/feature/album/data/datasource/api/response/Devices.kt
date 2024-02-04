package com.yannick.cosmo.feature.album.data.datasource.api.response

import com.yannick.cosmo.feature.album.data.datasource.api.model.Device
import kotlinx.serialization.Serializable

@Serializable
data class Devices(
    val devices: List<Device>
)