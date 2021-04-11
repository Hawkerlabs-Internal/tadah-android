package com.hawkerlabs.tadah.domain.list_item

import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ListItems
import kotlinx.coroutines.flow.Flow

interface ListItemUseCase {

    suspend fun getItems(listId : String) : Flow<ListItems>

    suspend fun saveItem(list : Item)
}