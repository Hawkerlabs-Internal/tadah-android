package com.hawkerlabs.tadah.data.repository

import com.hawkerlabs.tadah.data.database.dao.ListItemDao
import com.hawkerlabs.tadah.data.database.dao.ListDao
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListsRepository @Inject constructor(private val listsDao: ListDao, private val itemsDao: ListItemDao) {

    val tasksFlow: Flow<kotlin.collections.List<List>>
        get() = listsDao.getAllTasksFlow()


    suspend fun createList(list: List) = withContext(Dispatchers.IO) {
        listsDao.createList(list)
    }


    suspend fun editList(list: List) = withContext(Dispatchers.IO) {
        listsDao.editList(list)
    }

    suspend fun deleteList(list: List) = withContext(Dispatchers.IO) {
        listsDao.deleteList(list)
        itemsDao.deleteItemsForList(list.id) //delete items for list as well
    }



    suspend fun getItemsByListFromLocalStore(listId: String) : ItemsByList{
        return withContext(Dispatchers.IO) {
            listsDao.getItemsByList(listId)
        }
    }


    /**
     * Save an item
     */
    suspend fun saveItem(item: Item) = withContext(Dispatchers.IO) {
        itemsDao.saveItem(item)
    }

    /**
     * Update an item
     */
    suspend fun updateItem(item: Item) = withContext(Dispatchers.IO) {
        itemsDao.updateItem(item)
    }

    /**
     *
     */
    suspend fun deleteItem(item: Item) = withContext(Dispatchers.IO) {
        itemsDao.deleteItem(item)
    }
}