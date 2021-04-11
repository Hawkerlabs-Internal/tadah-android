package com.hawkerlabs.tadah.domain.lists

import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.domain.lists.model.TasksResponse
import kotlinx.coroutines.flow.Flow

interface ListsUseCase {

    suspend fun createList(list : List)

    suspend fun deleteList(list : List)

    suspend fun getAllTasks() : Flow<TasksResponse>
}