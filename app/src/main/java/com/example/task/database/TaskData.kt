package com.example.task.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_data")
data class TaskData(

    @PrimaryKey(autoGenerate = true)
    var taskId:Long=0L,

    @ColumnInfo(name = "taskName")
    var taskName:String="",

    @ColumnInfo(name = "taskStatus")
    var taskStatus:String="false",


    @ColumnInfo(name = "taskDescription")
    var taskDescription:String="",


    @ColumnInfo(name = "taskStartDate")
    var taskStartDate:Long = System.currentTimeMillis(),


    @ColumnInfo(name = "taskEndDate")
    var taskEndDate:Long = taskStartDate

)



