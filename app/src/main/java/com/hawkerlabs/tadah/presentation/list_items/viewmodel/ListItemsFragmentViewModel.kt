package com.hawkerlabs.tadah.presentation.list_items.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemsFragmentViewModel @Inject constructor(private val listsUseCase: ListsUseCase) : ViewModel() {

    fun deleteList(list: List) {
        viewModelScope.launch {
            listsUseCase.deleteList(list)
        }

    }
}