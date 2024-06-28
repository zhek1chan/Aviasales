package com.example.aviasales.presentation.tickets

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.R
import com.example.aviasales.data.Ticket
import com.example.aviasales.databinding.TicketItemBinding
import java.util.Locale

class TicketsViewHolder(private val binding: TicketItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Ticket) {
        if (item.badge == null) {
            binding.badge.visibility = View.GONE
        } else {
            binding.badge.visibility = View.VISIBLE
            binding.noteText.text = item.badge
        }
        binding.departureTime.text = item.departure.date
        binding.arrivalTime.text = item.arrival.date
        binding.airportFrom.text = item.departure.airport
        binding.airportTo.text = item.arrival.airport
        var amount = "%,d".format(Locale.US, item.price)
        amount = amount.replace(',', ' ')
        binding.amount.text = itemView.resources.getString(R.string.amount, amount)
    }
}