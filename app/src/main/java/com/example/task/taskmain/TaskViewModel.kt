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
    private val _tasks= MutableLiveData<List<TaskData>>()

    val tasks : LiveData<List<TaskData>>
        get()=_tasks


    init{
        state.value=false
    }
    fun get() {
        uiScope.launch {


                _tasks.value=database.getAllTasks()





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

    fun delete(taskId: Long){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.delete(taskId)

                get()
                Log.i("addtasks","delete $tasks")

            }
            state.value=true
        }

    }

    fun update(taskData : TaskData ){
        uiScope.launch {
            withContext(Dispatchers.IO) {


                database.update(taskData)


                get()


            }
            state.value=false
        }

    }


    var upDatestate=MutableLiveData<Boolean>()



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun upDate(it: TaskData) {
        it.taskStatus="true"
        it.taskEndDate=System.currentTimeMillis()
        Log.i("hello","upDate Called$it")
        update(it)

    }

}