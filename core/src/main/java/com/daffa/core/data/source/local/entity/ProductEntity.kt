package com.daffa.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(

    @PrimaryKey
    val id: Int,

    val price: Double,

    val description: String,

    val category: String,

    val imageUrl: String,

    val rate: Double,

    val rateCount: Int
)