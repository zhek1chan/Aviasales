package com.example.aviasales.domain.repository

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}