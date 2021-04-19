package com.hawkerlabs.tadah.presentation.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Create task use case for creating a task, as of now only locally
 */
@HiltViewModel
class CreateListViewModel @Inject constructor(private val tasksUseCase: ListsUseCase) : ViewModel() {
    val title = MutableLiveData("")
    val description = MutableLiveData("")

    fun saveTask() {
        viewModelScope.launch {
            tasksUseCase.createList(List(title = title.value.toString(), description = description.value.toString()))
        }
    }


}