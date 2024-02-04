package com.yannick.cosmo.feature.album.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yannick.cosmo.feature.album.domain.model.DeviceUiModel

@Entity(tableName = "devices")
data class DevicesEntityModel(
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
    @PrimaryKey(autoGenerate = true)
    val id: Int
)

fun DevicesEntityModel.toDeviceDomainModel() = DeviceUiModel(
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
    id = id
)
