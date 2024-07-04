package com.example.data.network

import com.example.data.dto.RecTicketsHolderDto
import com.example.data.dto.RecommendationDto
import com.example.data.dto.TicketsDto
import com.example.java.Resource

interface NetworkClient {
    suspend fun getRecs(): Resource<RecommendationDto>

    suspend fun getTicketsRecs(): Resource<RecTicketsHolderDto>

    suspend fun getTickets(): Resource<TicketsDto>
}