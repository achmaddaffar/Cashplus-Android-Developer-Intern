package com.daffa.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val imageUrl: String?,
    val rate: Double?,
    val rateCount: Int?
): Parcelable