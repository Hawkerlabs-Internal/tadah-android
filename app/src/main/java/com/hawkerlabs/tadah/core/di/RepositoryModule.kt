package com.hawkerlabs.tadah.core.di

import com.hawkerlabs.tadah.data.database.dao.TaskDao
import com.hawkerlabs.tadah.data.repository.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideTasksRepository(dao : TaskDao) : TasksRepository {
        return TasksRepository(dao)
    }
}