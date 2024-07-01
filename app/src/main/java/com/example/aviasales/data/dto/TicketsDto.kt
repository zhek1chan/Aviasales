package com.example.aviasales.data.dto

import com.example.aviasales.data.Price

data class TicketsDto(
    val tickets: List<TicketDTO>
)

data class Departure(
    val town: String,
    val date: String,
    val airport: String
)
data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)
data class Luggage (
    val hasLuggage: Boolean,
    val price: Price
)
data class HandLuggage (
    val hasHandLuggage: Boolean,
    val size: String
)
data class TicketDTO (
    val id: Int,
    val badge: String?,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    var isReturnable: Boolean,
    val isExchangeable: Boolean
)