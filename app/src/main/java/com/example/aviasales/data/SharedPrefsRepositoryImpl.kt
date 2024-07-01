package com.example.aviasales.data

import com.example.aviasales.domain.repository.SharedPrefsRepository

class SharedPrefsRepositoryImpl(
    private val sharedPrefsStorage: SharedPrefsStorage
) : SharedPrefsRepository {

    override fun getDepartureCity(): String {
        return sharedPrefsStorage.getDepartureCity() ?: String()
    }

    override fun setDepartureCity(city: String) {
        return sharedPrefsStorage.setDepartureCity(city)
    }
}