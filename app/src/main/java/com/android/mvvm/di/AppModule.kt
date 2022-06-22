package com.android.mvvm.di

import com.android.mvvm.data.remote.DrinksService
import com.android.mvvm.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesDrinksApi(remoteDataSource: RemoteDataSource): DrinksService {
        return remoteDataSource.buildService(DrinksService::class.java)
    }

}