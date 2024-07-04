package com.example.aviasales.data.network

import com.example.aviasales.data.dto.OfferDTO
import com.example.aviasales.data.dto.RecTicketDto
import com.example.aviasales.data.dto.RecTicketsHolderDto
import com.example.aviasales.data.dto.RecommendationDto
import com.example.aviasales.data.dto.TicketsDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("https://run.mocky.io/v3/cafdf18b-30e1-46db-ad36-6054af02c7f2")
    suspend fun getRecommendations(): Response<RecommendationDto>

    @GET("https://run.mocky.io/v3/9063ae8b-b7ae-498e-83a2-b4d840447648")
    suspend fun getTicketsOffers(): Response<RecTicketsHolderDto>

    @GET("https://run.mocky.io/v3/96e52b71-b11f-4922-b5d2-48ba02ed553e")
    suspend fun getTickets(): Response<TicketsDto>
}