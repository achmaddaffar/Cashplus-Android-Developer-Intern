package com.daffa.core.data.source.local

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.local.room.ProductDao
import com.daffa.core.data.source.local.room.ProductDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDatabase: ProductDatabase
) {

    private val productDao = productDatabase.productDao()

    fun getAllCartItems(): Flow<List<ProductEntity>> = productDao.getAllCartItems()
    suspend fun insertCartItem(product: ProductEntity) = productDao.insertCartItem(product)
    suspend fun deleteCartItem(product: ProductEntity) = productDao.deleteCartItem(product)
}