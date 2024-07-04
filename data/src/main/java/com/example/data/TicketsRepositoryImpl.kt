package com.example.data

import com.example.data.dto.DtoMappers
import com.example.data.network.NetworkClient
import com.example.java.Resource
import com.example.java.model.Ticket
import com.example.java.repository.TicketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DtoMappers
) : TicketsRepository {

    override suspend fun getTickets(): Flow<Resource<List<Ticket>>> = flow {
        when (val response = networkClient.getTickets()) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.ticketsDTOToTickets(this.value.tickets)
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