package com.example.aviasales.data.network

import com.example.aviasales.data.dto.RecTicketsHolderDto
import com.example.aviasales.data.dto.RecommendationDto
import com.example.aviasales.data.dto.TicketsDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getRecommendations(): Response<RecommendationDto>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): Response<RecTicketsHolderDto>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Response<TicketsDto>
}