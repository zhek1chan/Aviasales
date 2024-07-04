package com.example.aviasales.presentation.main

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aviasales.R
import com.example.aviasales.databinding.RecomendationItemBinding
import com.example.java.model.Recommendation
import java.util.Locale

class RecsViewHolder(private val binding: RecomendationItemBinding) :
RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Recommendation) {
        binding.recTitle.text = item.title
        binding.recCity.text = item.town
        var amount = "%,d".format(Locale.US, item.price)
        amount = amount.replace(',', ' ')
        binding.recPrice.text = itemView.resources.getString(R.string.amount, amount)
        val cornerPixelSize =
            itemView.resources.getDimensionPixelSize(R.dimen.rec_cover_corner_radius)
        var picture: Drawable = when (item.id) {
            "1" -> {
                itemView.resources.getDrawable(R.drawable.dora)
            }
            "2" -> {
                itemView.resources.getDrawable(R.drawable.socratilera)
            }
            else -> {
                itemView.resources.getDrawable(R.drawable.yaneznauktoeto)
            }
        }
        Glide.with(itemView)
            .load(picture)
            .placeholder(R.drawable.search_rounded_icons)
            .transform(CenterCrop(), RoundedCorners(cornerPixelSize))
            .into(binding.recImage)
    }
}