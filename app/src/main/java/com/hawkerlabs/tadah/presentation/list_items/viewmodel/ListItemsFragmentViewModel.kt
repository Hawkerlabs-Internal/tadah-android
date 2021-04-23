package com.hawkerlabs.tadah.presentation.list_items.viewmodel

import androidx.lifecycle.*
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import com.hawkerlabs.tadah.domain.list_item.ListItemUseCase
import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemsFragmentViewModel @Inject constructor(private val listsUseCase: ListsUseCase,
                                                     private val listItemUseCase: ListItemUseCase) : ViewModel() {
    val item = MutableLiveData<String>("")


    private val listLiveData = MutableLiveData<List>()

    val items: LiveData<kotlin.collections.List<Item>> = listLiveData.switchMap { list ->
        liveData {
            val repos = listItemUseCase.getItems(list.id).asLiveData(Dispatchers.Main)
            emitSource(repos)
        }
    }


    fun deleteList(list: List) {
        viewModelScope.launch {
            listsUseCase.deleteList(list)
        }

    }


    /**
     * Delete list item
     */
    fun deleteListItem(index: Int) {
        viewModelScope.launch {
            listItemUseCase.deleteItem(index)
        }


        // Update the list item count
        viewModelScope.launch {
            var list = listLiveData.value
            list?.itemsCount = list?.itemsCount?.minus(1)
            viewModelScope.launch {
                listsUseCase.editList(list!!)
            }
        }

    }


    fun updateItem(item: Item) {
        viewModelScope.launch {
            listItemUseCase.updateItem(item)
        }
    }

    fun getItems(list: List) {
        listLiveData.postValue(list)
    }


    /**
     * Save item for a list
     */
    fun addItem(listId: String) {
        viewModelScope.launch {
            listItemUseCase.saveItem(Item(listId = listId, title = item.value))
        }

        viewModelScope.launch {
            var list = listLiveData.value
            list?.itemsCount = list?.itemsCount?.plus(1)
            viewModelScope.launch {
                listsUseCase.editList(list!!)
            }
        }

        item.postValue("")
    }
}