package com.hawkerlabs.tadah.presentation.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.data.database.entities.Task
import com.hawkerlabs.tadah.databinding.ListRowBinding

class ListsAdapter (private val listener: (Task) -> Unit): ListAdapter<Task, ListsViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(
                    oldItem: Task,
                    newItem: Task
            ): Boolean =
                    oldItem.title == newItem.title

            override fun areContentsTheSame(
                    oldItem: Task,
                    newItem: Task
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