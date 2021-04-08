package com.hawkerlabs.tadah.core.di

import com.hawkerlabs.tadah.domain.tasks.TasksUseCase
import com.hawkerlabs.tadah.domain.tasks.impl.TasksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun geTasksUseCase(getNearbyRestaurantsUseCase: TasksUseCaseImpl): TasksUseCase
}