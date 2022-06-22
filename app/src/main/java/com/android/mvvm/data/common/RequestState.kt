package com.android.mvvm.data.common

sealed class RequestState<T> {
    abstract val data: T?

    class Success<T>(override val data: T) : RequestState<T>()
    class Error<T>(val error: Throwable? = null, override val data: T? = null) : RequestState<T>()
}