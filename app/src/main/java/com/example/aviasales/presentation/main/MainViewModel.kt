package com.example.aviasales.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.java.Resource
import com.example.java.interactor.FromCityInteractor
import com.example.java.interactor.RecsInteractor
import com.example.java.model.Recommendation
import kotlinx.coroutines.launch

class MainViewModel(
    private val recsInteractor: RecsInteractor,
    private val fromCityInteractor: FromCityInteractor
) : ViewModel() {
    private val offersLiveData = MutableLiveData<List<Recommendation>>()

    fun observeOffers(): LiveData<List<Recommendation>> = offersLiveData

    fun getData() {
        viewModelScope.launch {
            recsInteractor.getRecommendations().collect { offers ->
                when (offers) {
                    is Resource.Data -> offersLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }

    fun getDepartureCity(): String {
        return fromCityInteractor.getFromCity()
    }

    fun setDepartureCity(city: String) {
        return fromCityInteractor.setFromCity(city)
    }
}