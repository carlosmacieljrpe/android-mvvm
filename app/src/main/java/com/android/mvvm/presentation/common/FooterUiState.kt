package com.android.mvvm.presentation.common

import android.content.Context
import android.view.View
import androidx.paging.LoadState
import com.android.mvvm.R

data class FooterUiState(private val loadState: LoadState) {

    fun getLoadingVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?: context.getString(R.string.something_went_wrong)
    } else ""

    private fun getViewVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}