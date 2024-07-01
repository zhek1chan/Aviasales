package com.example.aviasales.data

import com.example.aviasales.data.dto.DtoMappers
import com.example.aviasales.data.network.NetworkClient
import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.Ticket
import com.example.aviasales.domain.repository.TicketsRepository
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