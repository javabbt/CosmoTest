package com.yannick.cosmo.feature.album.domain.usecases

import com.yannick.cosmo.base.data.retrofit.ApiResult
import com.yannick.cosmo.feature.album.data.datasource.api.response.Devices
import com.yannick.cosmo.feature.album.data.datasource.api.service.DevicesApi

class GetDevicesUseCase(
    private val devicesApi: DevicesApi
) {
    suspend operator fun invoke(): ApiResult<Devices> {
        return devicesApi.getDevices()
    }
}