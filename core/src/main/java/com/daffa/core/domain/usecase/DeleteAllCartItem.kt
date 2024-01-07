package com.daffa.core.domain.usecase

import com.daffa.core.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteAllCartItem @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke() {
        repository.deleteAllCartItem()
    }
}