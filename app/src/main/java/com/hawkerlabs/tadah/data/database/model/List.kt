package com.hawkerlabs.tadah.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "lists")
class List(
        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
        val title: String? = null,
        val description: String? = null,
        var isCompleted: Boolean = false
): Parcelable


