package com.hawkerlabs.tadah.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hawkerlabs.tadah.data.database.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(review: Item)

    @Delete
    suspend fun removeItem(review: Item)
}