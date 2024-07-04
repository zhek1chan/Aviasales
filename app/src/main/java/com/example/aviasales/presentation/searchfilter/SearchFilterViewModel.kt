package com.example.aviasales.presentation.searchfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.java.Resource
import com.example.java.interactor.TicketsRecsInteractor
import com.example.java.model.TicketsRec
import kotlinx.coroutines.launch

class SearchFilterViewModel(
    private val ticketsRecsInteractor: TicketsRecsInteractor
) : ViewModel() {

    private val liveData = MutableLiveData<List<TicketsRec>>()

    fun observeData(): LiveData<List<TicketsRec>> = liveData

    fun getData() {
        viewModelScope.launch {
            ticketsRecsInteractor.getTicketsRec().collect { offers ->
                when (offers) {
                    is Resource.Data -> liveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }
}