package com.yannick.cosmo.feature.album.domain.model

data class DeviceUiModel(
    val brakeLight: Boolean?,
    val firmwareVersion: String?,
    val installationMode: String?,
    val lightAuto: Boolean?,
    val lightMode: String?,
    val lightValue: Int?,
    val macAddress: String?,
    val model: String?,
    val product: String?,
    val serial: String?,
    val id: Int
)
