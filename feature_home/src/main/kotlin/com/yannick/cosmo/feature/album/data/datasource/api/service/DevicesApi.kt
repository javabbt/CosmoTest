package com.yannick.cosmo.feature.album.data.datasource.api.service

import com.yannick.cosmo.base.data.retrofit.ApiResult
import com.yannick.cosmo.feature.album.data.datasource.api.response.Devices
import retrofit2.http.GET

interface DevicesApi {
    @GET("test/devices")
    suspend fun getDevices(): ApiResult<Devices>
}
