package com.hawkerlabs.tadah.data.repository

import android.util.Log
import com.hawkerlabs.tadah.data.database.dao.ListItemDao
import com.hawkerlabs.tadah.data.database.dao.ListDao
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.data.database.model.relations.ListItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListsRepository @Inject constructor(private val listsDao: ListDao, private val itemsDao: ListItemDao) {

    val tasksFlow: Flow<kotlin.collections.List<List>>
        get() = listsDao.getAllTasksFlow()

    private val listItemsFlow = MutableSharedFlow<ListItems>(replay = 1)

    suspend fun createList(list: List) = withContext(Dispatchers.IO) {
        listsDao.createList(list)
    }

    suspend fun deleteList(list: List) = withContext(Dispatchers.IO) {
        listsDao.deleteList(list)
    }






    fun getListItemsFlow(listId: String): Flow<ListItems> {
        return itemsDao.getItemsForListFlow(listId)
    }
    /**
     * Save an item
     */
    suspend fun saveItem(item: Item) = withContext(Dispatchers.IO) {
        itemsDao.saveItem(item)
    }
}