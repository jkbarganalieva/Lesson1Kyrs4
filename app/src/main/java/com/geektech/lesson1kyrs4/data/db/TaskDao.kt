package com.geektech.lesson1kyrs4.data.db

import androidx.room.*
import com.geektech.lesson1kyrs4.model.Task


@Dao
interface TaskDao {

    @Query("SELECT*FROM task")
    fun getAll():List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}