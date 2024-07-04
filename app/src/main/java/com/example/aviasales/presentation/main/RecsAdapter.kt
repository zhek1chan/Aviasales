package com.example.aviasales.presentation.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.databinding.RecomendationItemBinding
import com.example.java.model.Recommendation

class RecsAdapter : RecyclerView.Adapter<RecsViewHolder>() {

    private var items: ArrayList<Recommendation> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecsViewHolder {
        return RecsViewHolder(
            RecomendationItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(list: List<Recommendation>) {
        items.addAll(list)
        notifyDataSetChanged()
        Log.d("List", "$items")
    }
}