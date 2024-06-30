package com.example.aviasales.presentation.searchfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.interactor.TicketsRecsInteractor
import com.example.aviasales.domain.model.TicketsRec
import kotlinx.coroutines.launch

class SearchFilterViewModel(
    private val ticketsRecsInteractor: TicketsRecsInteractor
) : ViewModel() {

    private val liveData = MutableLiveData<List<TicketsRec>>()

    fun observeData(): LiveData<List<TicketsRec>> = liveData

    fun getData() {
        viewModelScope.launch {
            ticketsRecsInteractor.getTicketsOffers().collect { offers ->
                when (offers) {
                    is Resource.Data -> liveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }
}