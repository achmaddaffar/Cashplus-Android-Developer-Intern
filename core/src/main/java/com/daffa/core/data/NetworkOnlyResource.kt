package com.daffa.core.data

import com.daffa.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val result = createCall().first()) {
            is ApiResponse.Error -> {
                emit(Resource.Error(result.errorMessage))
            }

            is ApiResponse.Success -> {
                emitAll(
                    loadFromNetwork(result.data).map {
                        Resource.Success(it)
                    }
                )
            }
        }
    }

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}