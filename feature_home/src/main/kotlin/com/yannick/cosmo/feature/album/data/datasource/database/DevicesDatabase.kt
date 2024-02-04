package com.yannick.cosmo.feature.album.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yannick.cosmo.feature.album.data.datasource.database.model.DevicesEntityModel

@Database(entities = [DevicesEntityModel::class], version = 1, exportSchema = false)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun devicesDao(): DevicesDao
}
