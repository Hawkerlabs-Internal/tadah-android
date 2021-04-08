package com.hawkerlabs.tadah.presentation.list.ui

import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.databinding.ListRowBinding

class ListsViewHolder(private val binding : ListRowBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item : Task,  listener : (Task) -> Unit){
        with(binding){
            task = item
            binding.cardLayout.setOnClickListener {
                listener(task!!)
            }
        }
    }
}