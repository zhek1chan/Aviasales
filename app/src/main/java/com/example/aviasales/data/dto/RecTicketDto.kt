package com.example.aviasales.data.dto

import com.example.aviasales.data.Price

data class RecTicketDto(
    val id: String,
    val title: String,
    val timeRange: ArrayList<String>,
    val price: Price
)