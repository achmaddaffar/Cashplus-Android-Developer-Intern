package com.daffa.core.data

import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import com.daffa.core.data.mapper.ProductMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {
    override fun getAllProducts(): Flow<Resource<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, List<ProductResponseItem>>() {
            override fun loadFromDB(): Flow<List<Product>> = localDataSource.getAllCartItems().map {
                ProductMapper.mapEntitiesToDomain(it)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProducts()

            override suspend fun saveCallResult(data: List<ProductResponseItem>) {
                val productList = ProductMapper.mapResponsesToEntity(data)
                localDataSource.insertCartItems(productList)
            }

            override fun shouldFetch(data: List<Product>?): Boolean = data.isNullOrEmpty()

        }
            .asFlow()
            .flowOn(Dispatchers.IO)
    }

    override fun getAllCartItems(): Flow<List<Product>> {
        return localDataSource.getAllCartItems().map {
            ProductMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getCartItem(id: Int): Flow<Product> {
        return localDataSource.getCartItem(id).map {
            ProductMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertCartItem(product: Product) {
        localDataSource.insertCartItem(
            ProductMapper.mapDomainToEntity(product)
        )
    }

    override fun addCartItem(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.addCartItem(
                ProductMapper.mapDomainToEntity(product)
            )
        }
    }

    override fun subtractCartItem(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.subtractCartItem(
                ProductMapper.mapDomainToEntity(product)
            )
        }
    }

    override fun getCartResult(): Flow<List<Product>> {
        return localDataSource.getCartResult().map {
            ProductMapper.mapEntitiesToDomain(it)
        }
    }

    override fun deleteAllCartItem() {
        localDataSource.deleteAllCartItem()
    }
}