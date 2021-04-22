package com.example.task

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.database.TaskData

import java.text.SimpleDateFormat


@BindingAdapter("bind")
fun bindEndTime(textView: TextView, taskData: TaskData) {
    if(taskData.taskStartDate!=taskData.taskEndDate){
        val string= SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(taskData.taskEndDate).toString()

        textView.text=string
    }
    else{
        textView.text="Not Completed"
    }


}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<TaskData>?) {
    val adapter = recyclerView.adapter as TaskAdapter
    Log.i("hello","updated${data}")
    adapter.submitList(data)
}

@BindingAdapter("startTime")
fun bindStartTime(textView: TextView, long: Long) {

        val string= SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(long).toString()
        textView.text=string



}

@BindingAdapter("name")
fun bindName(textView: TextView, name: String) {

    textView.text=name

}
@BindingAdapter("desc")
fun binddesc(textView: TextView, name: String) {

    textView.text=name

}

//@BindingAdapter("startTime")
//fun bindStartTime(textView: TextView, long: Long) {
//
//    val string= SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
//        .format(long).toString()
//    textView.text=string
//
//
//
//}