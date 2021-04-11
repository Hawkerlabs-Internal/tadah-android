package com.hawkerlabs.tadah.data.database.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.hawkerlabs.tadah.data.database.model.Item
import kotlinx.android.parcel.Parcelize

/**
 * One list has many items, this is a One to Many Relationship
 */
@Parcelize
data class ListItems (@Embedded
                      val list: com.hawkerlabs.tadah.data.database.model.List,

                      @Relation(
                             parentColumn = "id",
                             entityColumn = "listId"
                     )
                      val items: List<Item>): Parcelable