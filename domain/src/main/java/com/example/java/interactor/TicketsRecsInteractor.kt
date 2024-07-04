package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.TicketsRec
import kotlinx.coroutines.flow.Flow

interface TicketsRecsInteractor {
    suspend fun getTicketsRec(): Flow<Resource<List<TicketsRec>>>
}