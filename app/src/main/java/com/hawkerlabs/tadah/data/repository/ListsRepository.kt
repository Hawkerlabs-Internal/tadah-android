package com.hawkerlabs.tadah.data.repository

import com.hawkerlabs.tadah.data.database.dao.ListDao
import com.hawkerlabs.tadah.data.database.model.List
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListsRepository @Inject constructor(private val taskDao: ListDao) {

    val tasksFlow : Flow<kotlin.collections.List<List>>
        get() = taskDao.getAllTasksFlow()


    suspend fun createList(list: List) =  withContext(Dispatchers.IO) {
        taskDao.createList(list)
    }

    suspend fun deleteList(list: List) =  withContext(Dispatchers.IO) {
        taskDao.deleteList(list)
    }
}