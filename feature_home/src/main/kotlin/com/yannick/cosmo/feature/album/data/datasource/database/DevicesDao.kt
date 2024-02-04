package com.yannick.cosmo.feature.album.data.datasource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.yannick.cosmo.feature.album.data.datasource.database.model.DevicesEntityModel

@Dao
interface DevicesDao {
    @Query("SELECT * FROM devices")
    fun getAllDevices(): List<DevicesEntityModel>?

    @Upsert
    suspend fun upsertAll(devices: List<DevicesEntityModel>?)

    @Query("SELECT * FROM devices WHERE id = :id")
    suspend fun getContactDetails(id: Int): DevicesEntityModel?

    @Query("DELETE FROM devices")
    suspend fun clearAll()
}
