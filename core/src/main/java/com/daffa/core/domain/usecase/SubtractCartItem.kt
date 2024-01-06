package com.daffa.core.domain.usecase

import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import javax.inject.Inject

class SubtractCartItem @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(product: Product) {
        repository.subtractCartItem(product)
    }
}