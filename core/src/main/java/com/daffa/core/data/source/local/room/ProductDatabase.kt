package com.daffa.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daffa.core.data.source.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}