package com.example.aviasales.presentation.tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.data.Ticket
import com.example.aviasales.databinding.TicketItemBinding

class TicketsAdapter : RecyclerView.Adapter<TicketsViewHolder>() {

    private var items: List<Ticket> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return TicketsViewHolder(TicketItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Ticket>) {
        this.items = items
        notifyDataSetChanged()
    }
}