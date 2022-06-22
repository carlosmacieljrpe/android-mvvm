package com.android.mvvm.data.remote

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

// This class was created to reduce the responsibilities of DrinkAPI
// This class is responsible for creating the Retrofit service
class RemoteDataSource @Inject constructor(@ApplicationContext appContext: Context) {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(MockRequestInterceptor(appContext))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    companion object {
        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }
}