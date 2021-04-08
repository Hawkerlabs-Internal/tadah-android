package com.hawkerlabs.tadah.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tasks")
class Task(
        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
        val title: String? = null,
        val description: String? = null,
        val status: String? = null
): Parcelable


