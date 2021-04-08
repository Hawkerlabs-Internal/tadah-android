package com.hawkerlabs.tadah.domain.tasks

import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.domain.tasks.model.TasksResponse
import kotlinx.coroutines.flow.Flow

interface TasksUseCase {

    suspend fun addTask(task : Task)

    suspend fun getAllTasks() : Flow<TasksResponse>
}