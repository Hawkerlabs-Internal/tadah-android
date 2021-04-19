package com.hawkerlabs.tadah.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "items")
data class Item(@PrimaryKey val id: String = UUID.randomUUID().toString(),
                val listId: String,
                var title: String? = null,
                var isCompleted: Boolean = false) : Parcelable