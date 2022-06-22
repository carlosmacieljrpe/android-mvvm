package com.android.mvvm.presentation.drinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.R
import com.android.mvvm.domain.model.Drinks
import com.bumptech.glide.Glide

class DrinksAdapter :
    ListAdapter<Drinks, DrinksUsualViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksUsualViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drink_item, parent, false)
        return DrinksUsualViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinksUsualViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class DrinksUsualViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val drinkDescription: TextView = itemView.findViewById(R.id.drink_description)
    private val drinkThumbnail: ImageView = itemView.findViewById(R.id.drink_thumbnail)

    fun bind(drinks: Drinks) {
        drinkDescription.text = drinks.description
        drinkThumbnail.visibility = View.VISIBLE
        Glide
            .with(itemView.context)
            .load(drinks.drinkThumbnail)
            .centerCrop()
            .placeholder(R.drawable.abc_vector_test)
            .into(drinkThumbnail)
    }
}

val diffUtil = object : DiffUtil.ItemCallback<Drinks>() {
    override fun areItemsTheSame(oldItem: Drinks, newItem: Drinks): Boolean {
        return (oldItem as? Drinks)?.id == (newItem as? Drinks)?.id
    }

    override fun areContentsTheSame(oldItem: Drinks, newItem: Drinks): Boolean {
        return oldItem == newItem
    }
}