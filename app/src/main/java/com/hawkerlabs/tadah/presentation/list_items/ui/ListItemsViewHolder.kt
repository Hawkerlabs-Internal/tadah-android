package com.hawkerlabs.tadah.presentation.list_items.ui

import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.databinding.ListItemRowBinding

class ListItemsViewHolder (private val binding : ListItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem : ItemAndList){
        with(binding){
            itemAndList = listItem

        }
    }
}