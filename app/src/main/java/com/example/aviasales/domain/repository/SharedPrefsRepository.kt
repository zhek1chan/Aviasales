package com.example.aviasales.domain.repository


interface SharedPrefsRepository {
    fun getDepartureCity(): String
    fun setDepartureCity(city: String)
}