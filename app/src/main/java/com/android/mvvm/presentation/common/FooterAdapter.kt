package com.android.mvvm.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.R
import com.android.mvvm.databinding.ItemPagingFooterBinding

class FooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterViewHolder {
        val itemPagingFooterBinding = inflate<ItemPagingFooterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_paging_footer,
            parent,
            false
        )
        return FooterViewHolder(itemPagingFooterBinding, retry)
    }

}
