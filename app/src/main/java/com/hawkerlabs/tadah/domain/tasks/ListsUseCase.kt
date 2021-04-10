package com.hawkerlabs.tadah.domain.tasks

import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.domain.tasks.model.TasksResponse
import kotlinx.coroutines.flow.Flow

interface ListsUseCase {

    suspend fun createList(task : List)

    suspend fun getAllTasks() : Flow<TasksResponse>
}