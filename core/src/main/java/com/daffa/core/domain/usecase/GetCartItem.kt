package com.daffa.core.domain.usecase

import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItem @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(id: Int): Flow<Product> {
        return repository.getCartItem(id)
    }
}