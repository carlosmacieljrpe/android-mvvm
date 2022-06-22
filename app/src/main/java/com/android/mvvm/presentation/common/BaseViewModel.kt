package com.android.mvvm.presentation.common

import androidx.lifecycle.ViewModel
import com.android.mvvm.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

// Base class that contains boiler plate code for requesting in the background thread.
abstract class BaseViewModel(
    private val dispatchers: CoroutineContextProvider
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatchers.io + job
}