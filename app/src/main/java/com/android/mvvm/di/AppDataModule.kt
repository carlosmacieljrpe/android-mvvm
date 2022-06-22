package com.android.mvvm.di

import com.android.mvvm.data.remote.DrinksDataSource
import com.android.mvvm.data.remote.DrinksDataSourceImpl
import com.android.mvvm.data.repository.DrinksRepository
import com.android.mvvm.data.repository.DrinksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppDataModule {

    @Binds
    @Singleton
    abstract fun bindsDrinksDataSource(drinksDataSource: DrinksDataSourceImpl): DrinksDataSource

    @Binds
    @Singleton
    abstract fun bindsDrinksRepository(drinksRepository: DrinksRepositoryImpl): DrinksRepository

}