package com.hawkerlabs.tadah.presentation.create_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.domain.tasks.TasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateListViewModel @Inject constructor(private val tasksUseCase : TasksUseCase): ViewModel() {
    val title = MutableLiveData<String>("")
    val description = MutableLiveData<String>("")

    fun saveTask(){
        viewModelScope.launch {
            tasksUseCase.addTask(Task(title = title.value.toString(), description = description.value.toString(), status = TASK_TODO ))
        }
    }

    companion object {
       private  const val TASK_TODO = "TODO"
    }
}