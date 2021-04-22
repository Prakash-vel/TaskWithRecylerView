package com.example.task

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.database.TaskData
import com.example.task.databinding.ListItemTaskBinding
import kotlinx.android.synthetic.main.fragment_about.view.*

class TaskAdapter(val onClickListener: OnClickListener): ListAdapter<TaskData, TaskAdapter.ViewHolder>(TaskAdapterDiffCallBack()) {


    class OnClickListener(val clickListener: (taskData: TaskData) -> Unit) {
        fun onClick(taskData: TaskData) = clickListener(taskData)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }
    //hello


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataItem=getItem(position)
        Log.i("hello","on bind called$dataItem")
        if(dataItem.taskStatus!="true"){
            holder.binding.taskStatus.setOnCheckedChangeListener() { compoundButton: CompoundButton, b: Boolean ->
                if (b) {
                    onClickListener.onClick(dataItem)
                }
            }
        }


        holder.bind(dataItem)

    }



    class ViewHolder(val binding: ListItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(dataItem: TaskData ) {
            binding.taskData=dataItem
            Log.i("hello","bind in viewholder$dataItem")
            binding.taskStatus.isChecked = dataItem.taskStatus != "false"
            binding.executePendingBindings()


        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_task, parent, false)
                val binding=ListItemTaskBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

    }

}

class TaskAdapterDiffCallBack : DiffUtil.ItemCallback<TaskData>(){
    override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
        Log.i("hello","diffutil old$oldItem new$newItem")
        return oldItem == newItem
    }

}
