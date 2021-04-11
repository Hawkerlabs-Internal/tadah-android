package com.hawkerlabs.tadah.core.di

import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import com.hawkerlabs.tadah.domain.lists.impl.ListsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun geTasksUseCase(getNearbyRestaurantsUseCase: ListsUseCaseImpl): ListsUseCase
}