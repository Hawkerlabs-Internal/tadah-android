package com.hawkerlabs.tadah.domain.tasks.model

import com.hawkerlabs.tadah.data.database.model.List

sealed class TasksResponse {
    data class Success(val data: kotlin.collections.List<List>) : TasksResponse()
    data class Error(val error: Throwable?) : TasksResponse()
}