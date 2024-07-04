package com.example.java.repository

import com.example.java.Resource
import com.example.java.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}