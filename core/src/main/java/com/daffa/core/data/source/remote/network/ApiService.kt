package com.daffa.core.data.source.remote.network

import com.daffa.core.data.source.remote.response.ProductResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductResponseItem>?

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}