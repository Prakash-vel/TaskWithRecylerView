package com.example.task.addtasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.database.TaskDatabaseDao


class TaskViewModelFactory(

    private val dataSource: TaskDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel( dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
