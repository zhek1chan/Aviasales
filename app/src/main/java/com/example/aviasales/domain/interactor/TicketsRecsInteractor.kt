package com.example.aviasales.domain.interactor

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.TicketsRec
import kotlinx.coroutines.flow.Flow

interface TicketsRecsInteractor {
    suspend fun getTicketsRec(): Flow<Resource<List<TicketsRec>>>
}