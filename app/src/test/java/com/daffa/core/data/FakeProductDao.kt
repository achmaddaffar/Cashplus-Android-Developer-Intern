package com.daffa.core.data

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductDao : ProductDao {

    private var productData = mutableListOf<ProductEntity>()

    override fun getAllCartItems(): Flow<List<ProductEntity>> = flow { emit(productData) }


    override fun getCartItem(id: Int): Flow<ProductEntity> = flow { emit(productData[id]) }

    override suspend fun insertCartItem(product: ProductEntity) {
        productData.add(product)
    }

    override suspend fun insertCartItems(products: List<ProductEntity>) {
        productData.addAll(products)
    }

    override suspend fun deleteCartItem(id: Int) {
        productData.removeAt(id)
    }

    override fun getCartResult(): Flow<List<ProductEntity>> = flow {
        val result = productData.filter { it.cartCount > 0 }
        emit(result)
    }

    override fun deleteTable() {
        productData = mutableListOf()
    }

    override fun updateProduct(product: ProductEntity) {
        productData[product.id!!] = product
    }
}