package com.example.task.addtasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.database.TaskData
import com.example.task.database.TaskDatabaseDao
import kotlinx.coroutines.*

class TaskViewModel(

    private val database: TaskDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var state=MutableLiveData<Boolean>()
    var tasks: List<TaskData>?=null

    init{
        state.value=false
    }
    fun get() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                tasks=database.getAllTasks()

                Log.i("addtasks","$tasks")


            }
            state.value=true
        }

    }
    fun clear(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.clear()

                get()
                Log.i("addtasks","clear $tasks")

            }
            state.value=false
        }

    }




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}