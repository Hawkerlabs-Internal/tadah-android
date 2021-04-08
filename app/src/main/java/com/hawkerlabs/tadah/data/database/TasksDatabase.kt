package com.hawkerlabs.tadah.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hawkerlabs.tadah.data.database.dao.TaskDao
import com.hawkerlabs.tadah.data.database.entities.Task

@Database(entities = [Task::class], version = 1)

abstract class  TasksDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao

}