package com.example.task.taskmain

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.task.R
import com.example.task.TaskAdapter
import com.example.task.addtasks.TaskViewModel
import com.example.task.addtasks.TaskViewModelFactory
import com.example.task.database.TaskDatabase
import com.example.task.databinding.FragmentTitleBinding
import java.lang.Exception


class TitleFragment : Fragment() {

lateinit var taskViewModel: TaskViewModel
lateinit var binding: FragmentTitleBinding
lateinit var layout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            Log.i("addtasks", "oncreate called")
            binding = FragmentTitleBinding.inflate(inflater)
            binding.floatingActionButton.setOnClickListener { it: View ->
                Navigation.findNavController(it)
                    .navigate(R.id.action_titleFragment_to_addTaskFragment)
            }
        try {
            val application = requireNotNull(this.activity).application

            val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao

//hello

            val viewModelFactory = TaskViewModelFactory(dataSource)

            taskViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)
            binding.lifecycleOwner = this

            taskViewModel.get()
            setHasOptionsMenu(true)
            val adapter=TaskAdapter((TaskAdapter.OnClickListener {
                taskViewModel.upDate(it)

            }))
            binding.tasks.adapter=adapter
            binding.taskViewModel=taskViewModel

//            taskViewModel.state.observe(this.viewLifecycleOwner, Observer {
//            if(taskViewModel.state.value == true){
//                adapter.submitList(taskViewModel.tasks)
//                Log.i("hello","updated${taskViewModel.tasks}")
//            }
//        })

        }
        catch (e: Exception){
            Log.i("addtask","error $e")
        }
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.clear_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.clear -> taskViewModel.clear()
        }

        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

//
//    fun updateUi(){
//        var tasks= taskViewModel.tasks
//        Log.i("addtasks","updateui called $tasks")
//        tasks?.forEach {
//            when(it.taskId){
//
//                1L -> binding.checkBox1.setText(it.taskName)
//                2L -> binding.checkBox2.setText(it.taskName)
//                3L -> binding.checkBox3.setText(it.taskName)
//                4L -> binding.checkBox4.setText(it.taskName)
//                5L -> binding.checkBox5.setText(it.taskName)
//                else -> binding.checkBox1.setText(it.taskName)
//            }
//            Log.i("addtask","when called")
//        }
//    }





}