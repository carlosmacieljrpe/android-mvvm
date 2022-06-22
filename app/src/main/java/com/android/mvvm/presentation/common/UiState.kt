package com.android.mvvm.presentation.common

sealed class UiState<T> {
    abstract val data: T?

    class Success<T>(override val data: T) : UiState<T>()
    class Loading<T>(override val data: T? = null) : UiState<T>()
    class Error<T>(val error: Throwable? = null, override val data: T? = null) : UiState<T>()
}