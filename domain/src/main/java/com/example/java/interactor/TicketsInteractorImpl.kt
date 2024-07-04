package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.Ticket
import com.example.java.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow

class TicketsInteractorImpl(
    private val repository: TicketsRepository
) : TicketsInteractor {
    override suspend fun getTickets(): Flow<Resource<List<Ticket>>> = repository.getTickets()
}