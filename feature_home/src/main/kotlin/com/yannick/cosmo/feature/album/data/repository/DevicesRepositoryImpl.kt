package com.yannick.cosmo.feature.album.data.repository

import androidx.room.withTransaction
import com.yannick.cosmo.base.data.retrofit.ApiResult
import com.yannick.cosmo.feature.album.data.datasource.api.model.toEntity
import com.yannick.cosmo.feature.album.data.datasource.api.response.Devices
import com.yannick.cosmo.feature.album.data.datasource.api.service.DevicesApi
import com.yannick.cosmo.feature.album.data.datasource.database.DevicesDatabase
import com.yannick.cosmo.feature.album.data.datasource.database.model.toDeviceDomainModel
import com.yannick.cosmo.feature.album.domain.model.DeviceUiModel
import com.yannick.cosmo.feature.album.domain.repository.DevicesRepository
import com.yannick.cosmo.feature.album.domain.usecases.GetDevicesUseCase
import com.yannick.cosmo.feature.album.domain.utils.Result
import com.yannick.cosmo.feature.home.R

class DevicesRepositoryImpl(
    private val devicesDatabase: DevicesDatabase,
    private val getDevicesUseCase: GetDevicesUseCase
) : DevicesRepository {
    override suspend fun getDevices(): Result<List<DeviceUiModel>> {
        return when (val result = getDevicesUseCase()) {
            is ApiResult.Success -> {
                devicesDatabase.withTransaction {
                    devicesDatabase.devicesDao().clearAll()
                    devicesDatabase.devicesDao().upsertAll(result.data.devices.map { it.toEntity() })
                    devicesDatabase.devicesDao().getAllDevices()?.map {
                        it.toDeviceDomainModel()
                    }?.let {
                        Result.Success(it)
                    } ?: Result.UnexpectedError(R.string.unexpected_error)
                }
            }

            is ApiResult.Error -> {
                val devices = devicesDatabase.devicesDao().getAllDevices()
                if(devices.isNullOrEmpty()) {
                    result.message?.let {
                        Result.Error(it)
                    } ?: Result.UnexpectedError(R.string.unexpected_error)
                } else {
                    Result.Success(
                        devices.map {
                            it.toDeviceDomainModel()
                        }
                    )
                }
            }

            is ApiResult.Exception -> {
                val devices = devicesDatabase.devicesDao().getAllDevices()
                if(devices.isNullOrEmpty()) {
                    result.throwable.message?.let {
                        Result.Error(
                            it
                        )
                    } ?: Result.UnexpectedError(
                        R.string.unexpected_error
                    )
                } else {
                    Result.Success(
                        devices.map {
                            it.toDeviceDomainModel()
                        }
                    )
                }
            }
        }
    }

    override suspend fun getDeviceDetail(id: Int): Result<DeviceUiModel> {
        return devicesDatabase.devicesDao().getContactDetails(id)?.toDeviceDomainModel()?.let {
            Result.Success(it)
        } ?: Result.UnexpectedError(R.string.unexpected_error)
    }
}
