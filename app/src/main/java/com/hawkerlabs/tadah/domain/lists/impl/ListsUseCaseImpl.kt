package com.hawkerlabs.tadah.domain.lists.impl

import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.data.repository.ListsRepository
import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import com.hawkerlabs.tadah.domain.lists.model.TasksResponse
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListsUseCaseImpl @Inject constructor(private val repository : ListsRepository): ListsUseCase {

    private val tasks : Flow<TasksResponse>
        get() =  repository.tasksFlow.map {
            TasksResponse.Success(it)
        }.catch {
            TasksResponse.Error(it.cause)
        }


    override suspend fun createList(list: List) {
        repository.createList(list)
    }

    override suspend fun editList(list: List) {
        repository.editList(list)
    }

    override suspend fun deleteList(list: List) {
        repository.deleteList(list)
    }

    override suspend fun getAllTasks(): Flow<TasksResponse> {
        return tasks
    }
}