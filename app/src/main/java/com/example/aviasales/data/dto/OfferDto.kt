package com.example.aviasales.data.dto

import com.example.aviasales.data.Price

data class OfferDTO(
    val id: String,
    val title: String,
    val town: String,
    val price: Price
)