package com.android.mvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.mvvm.data.common.RequestState
import com.android.mvvm.data.model.DrinksDataModel
import com.android.mvvm.data.model.DrinksListDataModel
import com.android.mvvm.data.remote.DrinksDataSource
import com.android.mvvm.data.remote.DrinksDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DrinksRepository {
    suspend fun getDrinks(): RequestState<DrinksListDataModel>
    suspend fun getDrinksFlow(): Flow<RequestState<DrinksListDataModel>>
    suspend fun getDrinksPaginated(): Flow<PagingData<DrinksDataModel>>
}

class DrinksRepositoryImpl @Inject constructor(
    private val drinksDataSource: DrinksDataSource
) : DrinksRepository {

    override suspend fun getDrinks(): RequestState<DrinksListDataModel> {
        return drinksDataSource.getDrinks()
    }

    override suspend fun getDrinksFlow(): Flow<RequestState<DrinksListDataModel>> {
        return flow<RequestState<DrinksListDataModel>> {
            emit(drinksDataSource.getDrinks())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDrinksPaginated(): Flow<PagingData<DrinksDataModel>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { drinksDataSource as DrinksDataSourceImpl }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}