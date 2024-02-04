package com.yannick.cosmo.feature.album.data.datasource.api.model

import com.yannick.cosmo.feature.album.data.datasource.database.model.DevicesEntityModel
import kotlinx.serialization.Serializable


@Serializable
data class Device(
    val brakeLight: Boolean? = false,
    val firmwareVersion: String?,
    val installationMode: String?,
    val lightAuto: Boolean? = false,
    val lightMode: String?,
    val lightValue: Int?,
    val macAddress: String?,
    val model: String?,
    val product: String?,
    val serial: String?
)

internal fun Device.toEntity() = DevicesEntityModel(
    brakeLight = brakeLight,
    firmwareVersion = firmwareVersion,
    installationMode = installationMode,
    lightAuto = lightAuto,
    lightMode = lightMode,
    lightValue = lightValue,
    macAddress = macAddress,
    model = model,
    product = product,
    serial = serial,
    id = 0
)