package com.example.data.dto

import com.example.data.Price
import com.google.gson.annotations.SerializedName

data class RecTicketDto(
    val id: String,
    val title: String,
    @SerializedName("time_range")
    val timeRange: ArrayList<String>,
    val price: Price
)