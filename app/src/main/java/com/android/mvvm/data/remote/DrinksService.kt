package com.android.mvvm.data.remote

import com.android.mvvm.data.model.DrinksListDataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DrinksService {
    // For the moment we are filtering only alcoholic drinks
    @Headers("mock:true")
    @GET("filter.php?a=Alcoholic")
    suspend fun get(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): DrinksListDataModel
}