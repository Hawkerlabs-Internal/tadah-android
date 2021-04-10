package com.hawkerlabs.tadah.presentation.list.ui

import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.databinding.ListRowBinding

class ListsViewHolder(private val binding : ListRowBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item : List, listener : (List) -> Unit){
        with(binding){
            task = item
            binding.cardLayout.setOnClickListener {
                listener(task!!)
            }
        }
    }
}