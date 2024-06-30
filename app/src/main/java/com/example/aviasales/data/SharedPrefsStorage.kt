package com.example.aviasales.data

import android.content.SharedPreferences

class SharedPrefsStorage(private val sharedPreferences: SharedPreferences) {

    fun getDepartureCity(): String? {
        return sharedPreferences.getString(CITY_FROM, null)
    }
    fun setDepartureCity(city: String) {
        sharedPreferences.edit()
            .putString(CITY_FROM, city)
            .apply()
    }

    companion object {
        const val CITY_FROM = "CITY_FROM"
    }
}