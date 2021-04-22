package com.hawkerlabs.tadah.domain.list_item.impl

import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import com.hawkerlabs.tadah.data.repository.ListsRepository
import com.hawkerlabs.tadah.domain.list_item.ListItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListItemUseCaseImpl @Inject constructor(private val repository: ListsRepository) : ListItemUseCase {

    private val _cache = mutableListOf<Item>()
    private val listItemsFlow = MutableSharedFlow<List<Item>>(replay = 1)


    /**
     * Emits the flow
     */
    private suspend fun requestData(listId: String) {
        repository.getItemsByListFromLocalStore(listId).let { itemsByList ->
            val items = itemsByList.items ?: emptyList()

            //Map it to a list of Items
            val listItems = items.map {
                Item(it.id, it.listId, it.title, it.isCompleted)
            }
            _cache.addAll(listItems)
            listItemsFlow.emit(_cache)
        }

    }


    override suspend fun getItems(listId: String): Flow<List<Item>> {
        requestData(listId)
        return listItemsFlow
    }

    /**
     * Save the item
     */
    override suspend fun saveItem(item: Item) {
        repository.saveItem(item)
        _cache.add(item)
        listItemsFlow.emit(_cache)
    }

    override suspend fun updateItem(item: Item) {
        repository.updateItem(item)
    }


}