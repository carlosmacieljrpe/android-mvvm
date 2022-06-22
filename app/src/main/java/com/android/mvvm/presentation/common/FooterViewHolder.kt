package com.android.mvvm.presentation.common

import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.databinding.ItemPagingFooterBinding

class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }

    fun <T : ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
        action()
        executePendingBindings()
    }
}