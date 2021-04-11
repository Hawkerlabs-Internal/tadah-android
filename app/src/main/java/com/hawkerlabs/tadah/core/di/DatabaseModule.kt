package com.hawkerlabs.tadah.core.di

import android.content.Context
import androidx.room.Room
import com.hawkerlabs.tadah.data.database.ListsDatabase
import com.hawkerlabs.tadah.data.database.dao.ListItemDao
import com.hawkerlabs.tadah.data.database.dao.ListDao
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
    fun provideListsDao(appDatabase: ListsDatabase): ListDao {
        return appDatabase.taskDao()
    }

    @Provides
    fun provideListItemsDao(appDatabase: ListsDatabase): ListItemDao {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ListsDatabase {
        return Room.databaseBuilder(
                appContext,
                ListsDatabase::class.java,
                DATABASE_NAME
        ).build()
    }

    companion object {
        private const val DATABASE_NAME = "Lists"

    }
}