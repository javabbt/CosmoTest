package com.yannick.cosmo.feature.album.data

import androidx.room.Room
import com.yannick.cosmo.feature.album.data.datasource.api.service.DevicesApi
import com.yannick.cosmo.feature.album.data.datasource.database.DevicesDatabase
import com.yannick.cosmo.feature.album.data.repository.DevicesRepositoryImpl
import com.yannick.cosmo.feature.album.domain.repository.DevicesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<DevicesRepository> { DevicesRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(DevicesApi::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            DevicesDatabase::class.java,
            "Devices.db",
        ).build()
    }

    single { get<DevicesDatabase>().devicesDao() }
}
