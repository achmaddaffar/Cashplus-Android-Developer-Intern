package com.daffa.core.data.source.remote

import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.data.source.remote.response.ProductResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllProducts(): Flow<ApiResponse<List<ProductResponseItem>>> = flow {
        try {
            val result = apiService.getAllProducts()
            if (result != null) {
                emit(ApiResponse.Success(result))
            } else {
                emit(ApiResponse.Error(null))
            }
        } catch (e: HttpException) {
            emit(ApiResponse.Error(e.toString()))
        } catch (e: IOException) {
            emit(ApiResponse.Error(e.toString()))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}