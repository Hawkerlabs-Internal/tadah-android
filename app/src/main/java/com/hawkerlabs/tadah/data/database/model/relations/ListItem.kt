package com.hawkerlabs.tadah.data.database.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.hawkerlabs.tadah.data.database.model.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem (@Embedded
                      val item: Item,
                     @Relation(
                             parentColumn = "listId",
                             entityColumn = "id"
                     )
                     val list: com.hawkerlabs.tadah.data.database.model.List): Parcelable