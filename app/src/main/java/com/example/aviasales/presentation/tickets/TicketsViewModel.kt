package com.example.aviasales.presentation.tickets

import androidx.lifecycle.ViewModel
import com.example.aviasales.data.Recommendation
import com.example.aviasales.data.Ticket

class TicketsViewModel : ViewModel() {
    fun getData(from: String, to: String): List<Ticket> {
        return emptyList()
    }
}