package com.example.data.dto

import com.example.data.Price

data class OfferDTO(
    val id: String,
    val title: String,
    val town: String,
    val price: Price
)