package com.example.java.repository

import com.example.java.Resource
import com.example.java.model.TicketsRec
import kotlinx.coroutines.flow.Flow

interface TicketsRecsRepository {

    suspend fun getTicketsOffers(): Flow<Resource<List<TicketsRec>>>
}