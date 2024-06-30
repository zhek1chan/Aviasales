package com.example.aviasales.domain.repository

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.TicketsRec
import kotlinx.coroutines.flow.Flow

interface TicketsRecsRepository {

    suspend fun getTicketsOffers(): Flow<Resource<List<TicketsRec>>>
}