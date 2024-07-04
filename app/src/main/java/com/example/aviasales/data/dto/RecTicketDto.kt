package com.example.aviasales.data.dto

import com.example.aviasales.data.Price
import com.google.gson.annotations.SerializedName

data class RecTicketDto(
    val id: String,
    val title: String,
    @SerializedName("time_range")
    val timeRange: ArrayList<String>,
    val price: Price
)