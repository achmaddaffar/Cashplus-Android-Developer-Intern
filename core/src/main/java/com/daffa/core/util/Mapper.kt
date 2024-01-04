package com.daffa.core.util

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object Mapper {

    fun mapResponsesToDomain(response: List<ProductResponseItem>): Flow<List<Product>> {
        val listOfProduct = response.map {
            Product(
                id = it.id,
                price = it.price,
                description = it.description,
                category = it.category,
                imageUrl = it.imageUrl,
                rate = it.rating?.rate,
                rateCount = it.rating?.count
            )
        }
        return flowOf(listOfProduct)
    }

    fun mapResponseToDomain(response: ProductResponseItem): Flow<Product> {
        return flowOf(
            Product(
                id = response.id,
                price = response.price,
                description = response.description,
                category = response.category,
                imageUrl = response.imageUrl,
                rate = response.rating?.rate,
                rateCount = response.rating?.count
            )
        )
    }

    fun mapEntitiesToDomain(entities: List<ProductEntity>): List<Product> =
        entities.map {
            Product(
                id = it.id,
                price = it.price,
                description = it.description,
                category = it.category,
                imageUrl = it.imageUrl,
                rate = it.rate,
                rateCount = it.rateCount
            )
        }

    fun mapEntityToDomain(entity: ProductEntity): Product =
        Product(
            id = entity.id,
            price = entity.price,
            description = entity.description,
            category = entity.category,
            imageUrl = entity.imageUrl,
            rate = entity.rate,
            rateCount = entity.rateCount
        )

    fun mapDomainToEntity(domain: Product) =
        ProductEntity(
            id = domain.id,
            price = domain.price,
            description = domain.description,
            category = domain.category,
            imageUrl = domain.imageUrl,
            rate = domain.rate,
            rateCount = domain.rateCount
        )
}