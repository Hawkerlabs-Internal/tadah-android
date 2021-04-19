package com.hawkerlabs.tadah.presentation.list_items.ui

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemAndList
import com.hawkerlabs.tadah.databinding.ListItemRowBinding

class ListItemsViewHolder(private val binding: ListItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: Item) {
        with(binding) {
            item = listItem

            itemName.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}