package com.hawkerlabs.tadah.presentation.list_items.ui

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.databinding.ListItemRowBinding

class ListItemsViewHolder(private val binding: ListItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: Item, listener: (Item) -> Unit) {
        with(binding) {
            item = listItem



            if (listItem.isCompleted) {
                itemName.isChecked = true
                itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            } else {
                itemName.isChecked = false
                itemName.paintFlags = itemName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }


            itemName.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {

                    itemName.paintFlags = itemName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

                }
                listItem.isCompleted = isChecked
                listener(listItem!!)
            }
        }
    }
}