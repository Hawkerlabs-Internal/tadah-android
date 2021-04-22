package com.hawkerlabs.tadah.domain.list_item

import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import kotlinx.coroutines.flow.Flow

interface ListItemUseCase {

    suspend fun getItems(listId : String) : Flow<List<Item>>

    suspend fun saveItem(list : Item)

    suspend fun updateItem(list : Item)
}