package com.example.data

import com.example.java.repository.SharedPrefsRepository

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