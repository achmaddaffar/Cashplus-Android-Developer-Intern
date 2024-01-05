package com.daffa.core.domain.usecase

import com.daffa.core.data.Resource
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCartItems @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): Flow<List<Product>> =
        repository.getAllCartItems()
}