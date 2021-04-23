package com.hawkerlabs.tadah.data.database.dao

import androidx.room.*
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {
    @Transaction
    @Query("SELECT * FROM items WHERE listId = :listId")
    suspend fun getItemsForListFlow(listId : String): ItemsByList


    @Query("DELETE FROM items WHERE listId = :listId")
    suspend fun deleteItemsForList(listId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(review: Item)

    @Delete
    suspend fun deleteItem(review: Item)
}