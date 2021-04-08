package com.hawkerlabs.tadah.domain.tasks.model

import com.hawkerlabs.tadah.data.database.entities.Task
import java.lang.Exception

sealed class TasksResponse {
    data class Success(val data: List<Task>) : TasksResponse()
    data class Error(val error: Throwable?) : TasksResponse()
}