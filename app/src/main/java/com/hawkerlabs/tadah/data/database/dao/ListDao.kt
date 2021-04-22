package com.hawkerlabs.tadah.data.database.dao

import androidx.room.*
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM lists")
     fun getAllTasksFlow(): Flow<kotlin.collections.List<List>>


    /**
     * Create
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun createList(task: List)

    /**
     * Edit
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editList(task: List)


    /**
     * Delete the list
     */
    @Delete
    suspend fun deleteList(task: List)


    /**
     * List Items
     */
    @Transaction
    @Query("SELECT * FROM lists WHERE id = :listId")
    suspend fun getItemsByList(listId: String): ItemsByList
}