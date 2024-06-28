package com.example.aviasales.data

data class Ticket(
    val badge: String?,
    val price: Int,
    val departure: DepartureOrArrival,
    val arrival: DepartureOrArrival,
    val hasTransfer: Boolean
)
