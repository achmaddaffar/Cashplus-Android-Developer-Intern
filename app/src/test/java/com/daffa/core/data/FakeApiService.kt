package com.daffa.core.data

import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.data.source.remote.response.Rating

class FakeApiService : ApiService {

    private val dummyResponse = DataDummy.generateDummyProductResponse()

    override suspend fun getAllProducts(): List<ProductResponseItem>? {
        return dummyResponse
    }
}