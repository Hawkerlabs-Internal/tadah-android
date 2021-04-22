package com.hawkerlabs.tadah.presentation.list_items.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.databinding.ListItemRowBinding

class ListItemsAdapter (private val listener: (Item) -> Unit): ListAdapter<Item, ListItemsViewHolder>(COMPARATOR) {

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
            holder.bind(item, listener)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                    oldItem: Item,
                    newItem: Item
            ): Boolean =
                    oldItem.title == newItem.title

            override fun areContentsTheSame(
                    oldItem: Item,
                    newItem: Item
            ): Boolean =
                    oldItem == newItem
        }
    }

}