package com.daffa.core.domain.repository

import com.daffa.core.data.Resource
import com.daffa.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<Resource<List<Product>>>
    fun getAllCartItems(): Flow<List<Product>>
    fun getCartItem(id: Int): Flow<Product>
    suspend fun insertCartItem(product: Product)
    fun addCartItem(product: Product)
    fun subtractCartItem(product: Product)
    fun getCartResult(): Flow<List<Product>>
    fun deleteAllCartItem()
}