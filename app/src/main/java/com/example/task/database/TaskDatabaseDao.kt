package com.example.task.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDatabaseDao {

    @Insert
    fun insert(task: TaskData)

    @Query("DELETE  FROM task_data WHERE taskStatus = 'true'")
    suspend fun clear()


    @Query("DELETE  FROM task_data WHERE taskId = :id")
    suspend fun delete(id: Long)


    @Query("SELECT * FROM task_data ORDER BY taskId DESC")
    suspend fun getAllTasks(): List<TaskData>

    @Update
    suspend fun update(task: TaskData)

    @Query("SELECT * FROM task_data WHERE taskId = :key")
    suspend fun get(key: Long):TaskData

}