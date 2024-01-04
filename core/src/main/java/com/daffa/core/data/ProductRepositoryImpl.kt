package com.daffa.core.data

import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import com.daffa.core.util.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ProductRepository {
    override fun getAllProducts(): Flow<Resource<List<Product>>> {
        return object : NetworkOnlyResource<List<Product>, List<ProductResponseItem>>() {
            override fun loadFromNetwork(data: List<ProductResponseItem>): Flow<List<Product>> {
                return Mapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponseItem>>> {
                return remoteDataSource.getAllProducts()
            }
        }.asFlow()
    }

    override fun getAllCartItems(): Flow<List<Product>> {
        return localDataSource.getAllCartItems().map {
            Mapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun insertCartItem(product: Product) {
        return localDataSource.insertCartItem(
            Mapper.mapDomainToEntity(product)
        )
    }

    override suspend fun deleteCartItem(product: Product): Int {
        return localDataSource.deleteCartItem(
            Mapper.mapDomainToEntity(product)
        )
    }
}