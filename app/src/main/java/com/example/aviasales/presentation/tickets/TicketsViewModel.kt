package com.example.aviasales.presentation.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.java.Resource
import com.example.java.interactor.TicketsInteractor
import com.example.java.model.Ticket
import kotlinx.coroutines.launch

class TicketsViewModel(private val ticksInteractor: TicketsInteractor) : ViewModel() {

    private val ticksLiveData = MutableLiveData<List<Ticket>>()

    fun observeTicks(): LiveData<List<Ticket>> = ticksLiveData

    fun getData() {
        viewModelScope.launch {
            ticksInteractor.getTickets().collect { offers ->
                when (offers) {
                    is Resource.Data -> ticksLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }
}