package com.hawkerlabs.tadah.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hawkerlabs.tadah.data.database.dao.ListDao
import com.hawkerlabs.tadah.data.database.dao.ListItemDao
import com.hawkerlabs.tadah.data.database.model.Item
import com.hawkerlabs.tadah.data.database.model.List

@Database(entities = [List::class, Item::class], version = 2, exportSchema = false)
abstract class  ListsDatabase : RoomDatabase(){
    abstract fun taskDao(): ListDao
    abstract fun itemDao(): ListItemDao

}