package com.example.aviasales.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.databinding.RecomendationItemBinding

class RecsAdapter : RecyclerView.Adapter<RecsViewHolder>() {

    private var items: List<Recommendation> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return RecsViewHolder(RecomendationItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: RecsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Recommendation>) {
        this.items = items
        notifyDataSetChanged()
    }
}