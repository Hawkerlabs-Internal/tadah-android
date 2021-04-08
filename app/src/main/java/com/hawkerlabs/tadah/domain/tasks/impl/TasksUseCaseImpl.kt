package com.hawkerlabs.tadah.domain.tasks.impl

import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.data.repository.TasksRepository
import com.hawkerlabs.tadah.domain.tasks.TasksUseCase
import com.hawkerlabs.tadah.domain.tasks.model.TasksResponse
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TasksUseCaseImpl @Inject constructor(private val repository : TasksRepository): TasksUseCase {

    private val tasks : Flow<TasksResponse>
        get() =  repository.tasksFlow.map {
            TasksResponse.Success(it)
        }.catch {
            TasksResponse.Error(it.cause)
        }


    override suspend fun addTask(task: Task) {
        repository.addTask(task)
    }

    override suspend fun getAllTasks(): Flow<TasksResponse> {
        return tasks
    }
}