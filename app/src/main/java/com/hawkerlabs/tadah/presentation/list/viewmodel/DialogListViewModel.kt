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
class DialogListViewModel @Inject constructor(private val tasksUseCase: ListsUseCase) : ViewModel() {
    val title = MutableLiveData("")
    val description = MutableLiveData("")

    lateinit var list : List

    /**
     * On Edit mode initialize Edit
     */
    fun initEdit(l : List) {
        title.postValue(l.title)
        description.postValue(l.description)
        list = l
    }

    /**
     * Init Edit used by ListItemsFragment
     */
    fun editList() {
        viewModelScope.launch {
            list.title = title.value.toString()
            list.description = description.value.toString()
            tasksUseCase.editList(list)
        }
    }

    /**
     * Save List
     */
    fun saveTask() {
        viewModelScope.launch {
            tasksUseCase.createList(List(title = title.value.toString(), description = description.value.toString()))
        }
    }


}