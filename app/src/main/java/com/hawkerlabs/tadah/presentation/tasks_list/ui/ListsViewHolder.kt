package com.hawkerlabs.tadah.presentation.tasks_list.ui

import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.databinding.ListItemBinding

class ListsViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item : Task){
        with(binding){
            task = item
        }
    }
}