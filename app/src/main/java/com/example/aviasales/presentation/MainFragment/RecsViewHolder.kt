package com.example.aviasales.presentation.MainFragment

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aviasales.R
import com.example.aviasales.databinding.RecomendationItemBinding
import java.util.Locale

class RecsViewHolder(private val binding: RecomendationItemBinding) :
RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Recommendation) {
        lateinit var picture: Drawable
        binding.recTitle.text = item.title
        binding.recCity.text = item.town
        var amount = "%,d".format(Locale.US, item.price)
        amount = amount.replace(',', ' ')
        binding.recPrice.text = itemView.resources.getString(R.string.amount, amount)
        val cornerPixelSize =
            itemView.resources.getDimensionPixelSize(R.dimen.rec_cover_corner_radius)
        if (item.id == 1) {
            picture = itemView.resources.getDrawable(R.drawable.dora)
        } else if (item.id == 2) {
            picture = itemView.resources.getDrawable(R.drawable.socratilera)
        } else {
            picture = itemView.resources.getDrawable(R.drawable.yaneznauktoeto)
        }
        Glide.with(itemView)
            .load(picture)
            .placeholder(R.drawable.search_rounded_icons)
            .centerCrop()
            .transform(RoundedCorners(cornerPixelSize))
            .into(binding.recImage)
    }
}