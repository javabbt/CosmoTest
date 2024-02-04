package com.yannick.cosmo.feature.album.domain.repository

import com.yannick.cosmo.feature.album.domain.model.DeviceUiModel
import com.yannick.cosmo.feature.album.domain.utils.Result

interface DevicesRepository {
    suspend fun getDevices(): Result<List<DeviceUiModel>>
    suspend fun getDeviceDetail(id: Int): Result<DeviceUiModel>
}
