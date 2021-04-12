package com.hawkerlabs.tadah.domain.list_item.impl

import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import com.hawkerlabs.tadah.data.repository.ListsRepository
import com.hawkerlabs.tadah.domain.list_item.ListItemUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListItemUseCaseImpl @Inject constructor(private val repository : ListsRepository): ListItemUseCase {

//    private val items : Flow<ListItemsResponse>
//        get() =  repository.getListItemsFlow().map {
//            ListItemsResponse.Success(it)
//        }.catch {
//            ListItemsResponse.Error(it.cause)
//        }

    override suspend fun getItems(listId: String) : Flow<List<ItemAndList>>{
        return repository.getItemsByListFlow(listId)
    }

    override suspend fun saveItem(item: Item) {
        repository.saveItem(item)
    }


}