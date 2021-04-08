package com.hawkerlabs.tadah.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hawkerlabs.tadah.data.database.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
     fun getAllTasksFlow(): Flow<List<Task>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addTask(task: Task)
}