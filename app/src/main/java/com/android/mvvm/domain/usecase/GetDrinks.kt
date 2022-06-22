package com.android.mvvm.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.android.mvvm.data.common.RequestState
import com.android.mvvm.data.repository.DrinksRepository
import com.android.mvvm.domain.mapper.DrinksMapper
import com.android.mvvm.domain.model.Drinks
import com.android.mvvm.presentation.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetDrinks {
    suspend operator fun invoke(): UiState<List<Drinks>>
    suspend fun requestFlow(): Flow<UiState<List<Drinks>>>
    suspend fun getPaginated(): Flow<PagingData<Drinks>>
}

class GetDrinksImpl @Inject constructor(
    private val drinksRepository: DrinksRepository
) : GetDrinks {
    override suspend fun invoke(): UiState<List<Drinks>> {
        val drinksList = drinksRepository.getDrinks()
        return if (drinksList is RequestState.Success) {
            UiState.Success(drinksList.data.drinks.map { DrinksMapper.toDomain(it) })
        } else {
            UiState.Error((drinksList as RequestState.Error).error)
        }
    }

    override suspend fun requestFlow(): Flow<UiState<List<Drinks>>> {
        return drinksRepository.getDrinksFlow().map { value ->
            if (value is RequestState.Success) {
                UiState.Success(value.data.drinks.map { DrinksMapper.toDomain(it) })
            } else {
                UiState.Error((value as RequestState.Error).error)
            }
        }
    }

    override suspend fun getPaginated() = drinksRepository.getDrinksPaginated().map { pagingData ->
        pagingData.map { DrinksMapper.toDomain(it) }
    }
}