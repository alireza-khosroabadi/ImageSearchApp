package com.alireza.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alireza.database.dao.image.ImageDao
import com.alireza.database.model.image.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDatabase:RoomDatabase() {

    abstract fun imageDao():ImageDao
}
