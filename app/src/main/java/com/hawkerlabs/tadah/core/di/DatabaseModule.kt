package com.hawkerlabs.tadah.core.di

import android.content.Context
import androidx.room.Room
import com.hawkerlabs.tadah.data.database.TasksDatabase
import com.hawkerlabs.tadah.data.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTaskDao(appDatabase: TasksDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TasksDatabase {
        return Room.databaseBuilder(
                appContext,
                TasksDatabase::class.java,
                DATABASE_NAME
        ).build()
    }

    companion object {
        private const val DATABASE_NAME = "Tasks"

    }
}