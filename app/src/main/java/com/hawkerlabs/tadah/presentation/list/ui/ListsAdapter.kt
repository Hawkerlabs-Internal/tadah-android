package com.hawkerlabs.tadah.presentation.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.databinding.ListRowBinding

class ListsAdapter (private val listener: (List) -> Unit): ListAdapter<List, ListsViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<List>() {
            override fun areItemsTheSame(
                    oldItem: List,
                    newItem: List
            ): Boolean =
                    oldItem.title == newItem.title

            override fun areContentsTheSame(
                    oldItem: List,
                    newItem: List
            ): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val binding: ListRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.list_row,
                parent, false
        )
        return ListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        val task = getItem(position)
        if (task != null) {
            holder.bind(task, listener)
        }
    }
}