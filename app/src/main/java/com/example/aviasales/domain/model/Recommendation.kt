package com.example.aviasales.domain.model

data class Recommendation(
    val id: String,
    val title: String,
    val town: String,
    val price: Int,
    val cover: String
)