package com.example.data

import com.example.data.dto.DtoMappers
import com.example.data.network.NetworkClient
import com.example.java.Resource
import com.example.java.model.Recommendation
import com.example.java.repository.RecsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DtoMappers
) : RecsRepository {
    override suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>> = flow {
        when (val response = networkClient.getRecs()) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.recommendationsDTOToMainRecommendations(this.value.offers)
                    emit(Resource.Data(data))
                }
            }

            is Resource.NotFound -> emit(Resource.NotFound(response.message))
            is Resource.ConnectionError -> {
                emit(Resource.ConnectionError(response.message))
            }
        }
    }.flowOn(Dispatchers.IO)
}