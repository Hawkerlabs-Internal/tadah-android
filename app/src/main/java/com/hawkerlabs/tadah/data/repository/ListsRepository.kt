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

    private val listItemsFlow = MutableSharedFlow<kotlin.collections.List<ItemAndList>>(replay = 1)

    suspend fun createList(list: List) = withContext(Dispatchers.IO) {
        listsDao.createList(list)
    }

    suspend fun deleteList(list: List) = withContext(Dispatchers.IO) {
        listsDao.deleteList(list)
    }





    private suspend fun requestData(listId: String) {

        withContext(Dispatchers.IO) {
            listsDao.getItemsByList(listId).let { itemsByList ->
                val items = itemsByList.items ?: emptyList()
                val listItems = items.map {
                    ItemAndList(it, itemsByList.list)
                }
                listItemsFlow.emit(listItems)
            }
        }

    }

   suspend fun getItemsByListFlow(listId: String): Flow<kotlin.collections.List<ItemAndList>> {

       requestData(listId)
        return listItemsFlow
    }
    /**
     * Save an item
     */
    suspend fun saveItem(item: Item) = withContext(Dispatchers.IO) {
        itemsDao.saveItem(item)
    }
}