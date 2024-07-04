package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsInteractor {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}