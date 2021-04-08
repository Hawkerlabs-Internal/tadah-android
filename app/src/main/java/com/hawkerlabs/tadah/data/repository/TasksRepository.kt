package com.hawkerlabs.tadah.data.repository

import com.hawkerlabs.tadah.data.database.dao.TaskDao
import com.hawkerlabs.tadah.data.database.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasksFlow : Flow<List<Task>>
        get() = taskDao.getAllTasksFlow()


    suspend fun addTask(task: Task) =  withContext(Dispatchers.IO) {
        taskDao.addTask(task)
    }
}