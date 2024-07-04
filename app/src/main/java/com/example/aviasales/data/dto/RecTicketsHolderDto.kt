package com.example.aviasales.data.dto

import com.google.gson.annotations.SerializedName

data class RecTicketsHolderDto(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<RecTicketDto>
)

