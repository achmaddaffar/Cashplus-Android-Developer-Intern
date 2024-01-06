package com.daffa.core.domain.usecase

import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import javax.inject.Inject

class AddCartItem @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(product: Product) = repository.insertCartItem(product)
}