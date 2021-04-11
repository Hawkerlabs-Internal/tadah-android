package com.hawkerlabs.tadah.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.hawkerlabs.tadah.domain.lists.ListsUseCase
import com.hawkerlabs.tadah.domain.lists.model.TasksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(private val listsUseCase : ListsUseCase): ViewModel(){


    val state: LiveData<TasksResponse> =
        liveData {
            val tasks = listsUseCase.getAllTasks()
                    .asLiveData(Dispatchers.Main)
            emitSource(tasks)
        }

}