package com.hawkerlabs.tadah.data.database.dao

import androidx.room.*
import com.hawkerlabs.tadah.data.database.model.List
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM lists")
     fun getAllTasksFlow(): Flow<kotlin.collections.List<List>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun createList(task: List)

    @Delete
    suspend fun deleteList(task: List)
}