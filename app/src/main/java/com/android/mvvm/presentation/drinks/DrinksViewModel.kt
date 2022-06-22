package com.android.mvvm.presentation.drinks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.mvvm.presentation.common.BaseViewModel
import com.android.mvvm.presentation.common.UiState
import com.android.mvvm.domain.model.Drinks
import com.android.mvvm.domain.usecase.FilterDrinks
import com.android.mvvm.domain.usecase.GetDrinks
import com.android.mvvm.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val getDrinks: GetDrinks,
    private val filterDrinks: FilterDrinks,
    dispatcher: CoroutineContextProvider,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel(dispatcher) {

    val drinksState: MutableLiveData<UiState<List<Drinks>>> by lazy {
        MutableLiveData()
    }

    val drinksFlowState: StateFlow<UiState<List<Drinks>>> =
        MutableStateFlow(UiState.Success(emptyList()))

    fun requestDrinks() {
        val drinksList = savedStateHandle.get<List<Drinks>>(DRINKS_LIST_KEY)
        if (drinksList != null) {
            drinksState.postValue(UiState.Success(drinksList))
        } else {
            drinksState.postValue(UiState.Loading())
            launch {
                val drinks = getDrinks()
                savedStateHandle[DRINKS_LIST_KEY] = drinks.data
                drinksState.postValue(drinks)
            }
        }
    }

    fun requestDrinksFlow() {
        val drinksList = savedStateHandle.get<List<Drinks>>(DRINKS_LIST_KEY)
        if (drinksList != null) {
            drinksState.postValue(UiState.Success(drinksList))
        } else {
            viewModelScope.launch {
                val drinks = getDrinks.requestFlow().single()
                if (drinks is UiState.Success) {
                    savedStateHandle[DRINKS_LIST_KEY] = drinks.data
                }
                drinksFlowState.mutable().emit(drinks)
            }
        }
    }

    fun filterDrinks(queryString: String) {
        viewModelScope.launch {
            val drinkList = savedStateHandle.get<List<Drinks>>(DRINKS_LIST_KEY)
            if (drinkList != null) {
                val filtered = filterDrinks(queryString, drinkList)
                drinksFlowState.mutable().emit(UiState.Success(filtered))
            }
        }
    }

    companion object {
        const val DRINKS_LIST_KEY = "drinkslist"
    }

    internal fun <T> StateFlow<T>.mutable() = this as MutableStateFlow<T>
}