package com.geektech.lesson1kyrs4.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.lesson1kyrs4.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
