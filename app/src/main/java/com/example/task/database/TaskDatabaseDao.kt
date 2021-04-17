package com.example.task.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDatabaseDao {

    @Insert
    fun insert(night: TaskData)

    @Query("DELETE  FROM task_data ")
    suspend fun clear()

    @Query("SELECT * FROM task_data ORDER BY taskId DESC")
    suspend fun getAllTasks(): List<TaskData>

}