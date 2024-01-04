package com.daffa.core.data.source.remote.network

import com.daffa.core.data.source.remote.response.ProductsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}