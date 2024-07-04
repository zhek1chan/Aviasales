package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.TicketsRec
import com.example.java.repository.TicketsRecsRepository
import kotlinx.coroutines.flow.Flow

class TicketsRecsInteractorImpl(
    private val repository: TicketsRecsRepository
) : TicketsRecsInteractor {
    override suspend fun getTicketsRec(): Flow<Resource<List<TicketsRec>>> =
        repository.getTicketsOffers()
}