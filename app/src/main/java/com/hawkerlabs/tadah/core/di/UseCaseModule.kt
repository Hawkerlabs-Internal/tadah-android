package com.hawkerlabs.tadah.core.di

import com.hawkerlabs.tadah.domain.list_item.ListItemUseCase
import com.hawkerlabs.tadah.domain.list_item.impl.ListItemUseCaseImpl
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
    abstract fun getListsUseCase(listsUseCase: ListsUseCaseImpl): ListsUseCase

    @Binds
    abstract fun getItemsUseCase(listItemUseCase: ListItemUseCaseImpl): ListItemUseCase
}