package com.hawkerlabs.tadah.domain.list_item.impl

import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ListItems
import com.hawkerlabs.tadah.data.repository.ListsRepository
import com.hawkerlabs.tadah.domain.list_item.ListItemUseCase
import com.hawkerlabs.tadah.domain.list_item.model.ListItemsResponse
import com.hawkerlabs.tadah.domain.lists.model.TasksResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListItemUseCaseImpl @Inject constructor(private val repository : ListsRepository): ListItemUseCase {

//    private val items : Flow<ListItemsResponse>
//        get() =  repository.getListItemsFlow().map {
//            ListItemsResponse.Success(it)
//        }.catch {
//            ListItemsResponse.Error(it.cause)
//        }

    override suspend fun getItems(listId: String) : Flow<ListItems>{
        return repository.getListItemsFlow(listId)
    }

    override suspend fun saveItem(item: Item) {
        repository.saveItem(item)
    }


}