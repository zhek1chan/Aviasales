package com.example.aviasales.domain.interactor

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsInteractor {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}