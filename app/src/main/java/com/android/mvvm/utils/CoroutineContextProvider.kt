package com.android.mvvm.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider @Inject constructor() {
    open val main: CoroutineContext = Dispatchers.Main
    open val io: CoroutineContext = Dispatchers.Default
}