package com.daffa.core.data.source.local

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.local.room.ProductDao
import com.daffa.core.data.source.local.room.ProductDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

    fun getAllCartItems(): Flow<List<ProductEntity>> = productDao.getAllCartItems()

    fun getCartItem(id: Int): Flow<ProductEntity> = productDao.getCartItem(id)

    suspend fun insertCartItem(product: ProductEntity) = productDao.insertCartItem(product)

    suspend fun insertCartItems(products: List<ProductEntity>) = productDao.insertCartItems(products)

    fun addCartItem(productEntity: ProductEntity) {
        productEntity.cartCount++
        productDao.updateProduct(productEntity)
    }

    fun subtractCartItem(productEntity: ProductEntity) {
        productEntity.cartCount--
        productDao.updateProduct(productEntity)
    }

    fun getCartResult() = productDao.getCartResult()

    fun deleteAllCartItem() = productDao.deleteTable()
}