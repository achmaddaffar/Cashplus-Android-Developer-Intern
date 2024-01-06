package com.daffa.core.data.source.remote

import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.data.source.remote.response.ProductResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getAllProducts(): Flow<ApiResponse<List<ProductResponseItem>>> = channelFlow {
        try {
            val result = apiService.getAllProducts()
            if (result != null) {
                send(ApiResponse.Success(result))
            } else {
                send(ApiResponse.Error(null))
            }
        } catch (e: HttpException) {
            send(ApiResponse.Error(e.toString()))
        } catch (e: IOException) {
            send(ApiResponse.Error(e.toString()))
        } catch (e: Exception) {
            send(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}