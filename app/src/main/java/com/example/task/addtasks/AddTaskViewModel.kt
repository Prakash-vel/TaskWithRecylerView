


package com.example.task.addtasks

import android.util.Log
import com.example.task.database.TaskData
import androidx.lifecycle.ViewModel
import com.example.task.database.TaskDatabaseDao
import kotlinx.coroutines.*

class AddTaskViewModel(

    private val database: TaskDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
   init {
       Log.i("addtask","viewmodel")
   }
   // val taskData: List<TaskData>? =null

    fun insert(text: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                var taskData: TaskData = TaskData()
                taskData.taskName= text.toString()
                database.insert(taskData)

            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}