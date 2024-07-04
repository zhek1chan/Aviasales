package com.example.java.interactor

import com.example.java.repository.SharedPrefsRepository

class FromCityInteractorImpl(
    private val repository: SharedPrefsRepository
) : FromCityInteractor {
    override fun getFromCity(): String {
        return repository.getDepartureCity()
    }

    override fun setFromCity(city: String) {
        repository.setDepartureCity(city)
    }
}