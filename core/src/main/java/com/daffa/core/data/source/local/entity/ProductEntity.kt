package com.daffa.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daffa.core.util.Empty

@Entity(tableName = "product")
data class ProductEntity(

    @PrimaryKey
    val id: Int? = 0,

    val title: String? = String.Empty,

    val price: Double? = 0.0,

    val description: String? = String.Empty,

    val category: String? = String.Empty,

    val imageUrl: String? = String.Empty,

    val rate: Double? = 0.0,

    val rateCount: Int? = 0,

    val cartCount: Int? = 0
)