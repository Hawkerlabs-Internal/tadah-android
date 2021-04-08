package com.hawkerlabs.tadah.presentation.tasks_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.hawkerlabs.tadah.domain.tasks.TasksUseCase
import com.hawkerlabs.tadah.domain.tasks.model.TasksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(private val tasksUseCase : TasksUseCase): ViewModel(){


    val state: LiveData<TasksResponse> =
        liveData {
            val tasks = tasksUseCase.getAllTasks()
                    .asLiveData(Dispatchers.Main)
            emitSource(tasks)
        }

}