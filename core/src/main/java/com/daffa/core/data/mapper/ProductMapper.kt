package com.daffa.core.data.mapper

import com.daffa.core.data.source.local.entity.ProductEntity
import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object ProductMapper {

    fun mapResponsesToEntity(response: List<ProductResponseItem>): List<ProductEntity> =
        response.map {
            ProductEntity(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                category = it.category,
                imageUrl = it.imageUrl,
                rate = it.rating?.rate,
                rateCount = it.rating?.count,
                cartCount = 0
            )
        }

    fun mapEntitiesToDomain(entities: List<ProductEntity>): List<Product> =
        entities.map {
            Product(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                category = it.category,
                imageUrl = it.imageUrl,
                rate = it.rate,
                rateCount = it.rateCount,
                cartCount = it.cartCount
            )
        }

    fun mapEntityToDomain(entity: ProductEntity): Product =
        Product(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            description = entity.description,
            category = entity.category,
            imageUrl = entity.imageUrl,
            rate = entity.rate,
            rateCount = entity.rateCount,
            cartCount = entity.cartCount
        )

    fun mapDomainToEntity(domain: Product) =
        ProductEntity(
            id = domain.id,
            title = domain.title,
            price = domain.price,
            description = domain.description,
            category = domain.category,
            imageUrl = domain.imageUrl,
            rate = domain.rate,
            rateCount = domain.rateCount,
            cartCount = domain.cartCount
        )
}