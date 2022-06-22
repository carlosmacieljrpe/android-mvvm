package com.android.mvvm.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.mvvm.data.common.RequestState
import com.android.mvvm.data.model.DrinksDataModel
import com.android.mvvm.data.model.DrinksListDataModel
import javax.inject.Inject

interface DrinksDataSource {
    suspend fun getDrinks(): RequestState<DrinksListDataModel>
}

class DrinksDataSourceImpl @Inject constructor(
    private val drinksService: DrinksService
) : DrinksDataSource, PagingSource<Int, DrinksDataModel>() {

    override suspend fun getDrinks(): RequestState<DrinksListDataModel> {
        return try {
            RequestState.Success(drinksService.get(1, 10))
        } catch (exception: Exception) {
            // TODO Handle specific exceptions
            RequestState.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DrinksDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, DrinksDataModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        try {
            val response = drinksService.get(page, params.loadSize)
            return LoadResult.Page(
                data = response.drinks,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.drinks.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}