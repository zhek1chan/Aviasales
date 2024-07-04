package com.example.aviasales.presentation.tickets

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.R
import com.example.aviasales.databinding.TicketItemBinding
import com.example.java.model.Ticket

class TicketsViewHolder(private val binding: TicketItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Ticket) {
        if (item.badge == null) {
            binding.badge.visibility = View.GONE
        } else {
            binding.badge.visibility = View.VISIBLE
            binding.noteText.text = item.badge
        }
        var text =
            "<font color=#FFFFFFFF>${item.timeInFlight}ч в пути </font><font color=#9F9F9F>/</font><font color=#FFFFFFFF> ${itemView.resources.getString(R.string.without_transfer)}</font>"
        if (!item.transfer) {
            binding.timeInFlyight.text = Html.fromHtml(text)
        } else {
            binding.timeInFlyight.text = "${item.timeInFlight}${itemView.resources.getString(R.string.with_transfer)}"
        }
        binding.departureTime.text = item.departureDate
        binding.arrivalTime.text = item.arrivalDate
        binding.airportFrom.text = item.departureAirport
        binding.airportTo.text = item.arrivalAirport
        binding.amount.text = item.price
    }
}