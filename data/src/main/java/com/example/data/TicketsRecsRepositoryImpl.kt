package com.example.data

import com.example.data.dto.DtoMappers
import com.example.data.network.NetworkClient
import com.example.java.Resource
import com.example.java.model.TicketsRec
import com.example.java.repository.TicketsRecsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketsRecsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DtoMappers
) : TicketsRecsRepository {

    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketsRec>>> = flow {
        when (val response = networkClient.getTicketsRecs()) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.ticketRecDTOToTicketsRec(this.value.ticketsOffers)
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