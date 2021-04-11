package com.hawkerlabs.tadah.data.database.dao

import androidx.room.*
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.relations.ListItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {
    @Transaction
    @Query("SELECT * FROM items WHERE listId = :listId")
    fun getItemsForListFlow(listId : String): Flow<ListItems>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(review: Item)

    @Delete
    suspend fun removeItem(review: Item)
}