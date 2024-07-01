package com.example.aviasales.data.network

import com.example.aviasales.data.dto.RecTicketsHolderDto
import com.example.aviasales.data.dto.RecommendationDto
import com.example.aviasales.data.dto.TicketsDto

interface NetworkClient {
    suspend fun getRecs(): Resource<RecommendationDto>

    suspend fun getTicketsRecs(): Resource<RecTicketsHolderDto>

    suspend fun getTickets(): Resource<TicketsDto>
}