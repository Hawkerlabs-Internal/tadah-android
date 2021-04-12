package com.hawkerlabs.tadah.presentation.list_items.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.databinding.ListItemRowBinding

class ListItemsAdapter : ListAdapter<ItemAndList, ListItemsViewHolder>(COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemsViewHolder {
        val binding: ListItemRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.list_item_row,
                parent, false
        )
        return ListItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ItemAndList>() {
            override fun areItemsTheSame(
                    oldItem: ItemAndList,
                    newItem: ItemAndList
            ): Boolean =
                    oldItem.item.id == newItem.item.id

            override fun areContentsTheSame(
                    oldItem: ItemAndList,
                    newItem: ItemAndList
            ): Boolean =
                    oldItem == newItem
        }
    }

}