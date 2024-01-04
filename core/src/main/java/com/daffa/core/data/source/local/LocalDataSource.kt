package com.daffa.core.data.source.local

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val productDao: ProductDao
) {

    fun getAllCartItems(): Flow<List<ProductEntity>> = productDao.getAllCartItems()
    suspend fun insertCartItem(product: ProductEntity) = productDao.insertCartItem(product)
    suspend fun deleteCartItem(product: ProductEntity) = productDao.deleteCartItem(product)
}