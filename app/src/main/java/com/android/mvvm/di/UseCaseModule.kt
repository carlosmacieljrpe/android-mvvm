package com.android.mvvm.di

import com.android.mvvm.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindsFilterDrinksUseCase(filterDrinks: FilterDrinksImpl): FilterDrinks

    @Binds
    @Singleton
    abstract fun bindsGetDrinksUseCase(getDrinks: GetDrinksImpl): GetDrinks

}