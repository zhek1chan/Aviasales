package com.example.aviasales.presentation.tickets

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.R
import com.example.aviasales.databinding.TicketItemBinding
import com.example.aviasales.domain.model.Ticket
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
        if (!item.transfer) {
            binding.timeInFlyight.text =
                "${item.timeInFlight}${itemView.resources.getString(R.string.without_transfer)}"
        } else {
            binding.timeInFlyight.text = item.timeInFlight
        }
        binding.departureTime.text = item.departureDate
        binding.arrivalTime.text = item.arrivalDate
        binding.airportFrom.text = item.departureAirport
        binding.airportTo.text = item.arrivalAirport
        var amount = "%,d".format(Locale.US, item.price)
        amount = amount.replace(',', ' ')
        binding.amount.text = itemView.resources.getString(R.string.amount, amount)
    }
}