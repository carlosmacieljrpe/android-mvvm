package com.android.mvvm.presentation.drinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.R
import com.android.mvvm.databinding.DrinkItemBinding
import com.android.mvvm.domain.model.Drinks
import com.bumptech.glide.Glide

class DrinksPagingAdapter :
    PagingDataAdapter<Drinks, DrinksPagingViewHolder>(diffUtilPaging) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksPagingViewHolder {
        val binding = DrinkItemBinding.inflate(LayoutInflater.from(parent.context))
        return DrinksPagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinksPagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class DrinksPagingViewHolder(private val binding: DrinkItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(drinks: Drinks) {
        binding.drinkDescription.text = drinks.description
        binding.drinkThumbnail.visibility = View.VISIBLE
        Glide
            .with(itemView.context)
            .load(drinks.drinkThumbnail)
            .centerCrop()
            .placeholder(R.drawable.abc_vector_test)
            .into(binding.drinkThumbnail)
    }
}

val diffUtilPaging = object : DiffUtil.ItemCallback<Drinks>() {
    override fun areItemsTheSame(oldItem: Drinks, newItem: Drinks): Boolean {
        return (oldItem as? Drinks)?.id == (newItem as? Drinks)?.id
    }

    override fun areContentsTheSame(oldItem: Drinks, newItem: Drinks): Boolean {
        return oldItem == newItem
    }
}