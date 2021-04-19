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
    private val listIdLiveData = MutableLiveData<String>()

    val items: LiveData<kotlin.collections.List<Item>> = listIdLiveData.switchMap { listId ->
        liveData {
            val repos = listItemUseCase.getItems(listId).asLiveData(Dispatchers.Main)
            emitSource(repos)
        }
    }


    fun deleteList(list: List) {
        viewModelScope.launch {
            listsUseCase.deleteList(list)
        }

    }

    fun getItems(listId: String){
        listIdLiveData.postValue(listId)
    }


    /**
     * Save item for a list
     */
    fun addItem(listId : String) {
        viewModelScope.launch {
            listItemUseCase.saveItem(Item(listId = listId, title = item.value))
        }
    }
}