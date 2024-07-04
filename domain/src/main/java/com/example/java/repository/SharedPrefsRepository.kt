package com.example.java.repository


interface SharedPrefsRepository {
    fun getDepartureCity(): String
    fun setDepartureCity(city: String)
}