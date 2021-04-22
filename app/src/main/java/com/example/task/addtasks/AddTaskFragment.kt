package com.example.task.addtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.task.R
import com.example.task.database.TaskDatabase
import com.example.task.databinding.FragmentAddTaskBinding


class AddTaskFragment : Fragment() {

    lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao

        val viewModelFactory = AddTaskViewModelFactory(dataSource)

        val addTaskViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.addTaskViewModel = addTaskViewModel

        binding.button3.setOnClickListener {
            val name=binding.editTextName.text.toString()
            val desc=binding.editTextDesc.text.toString()


            addTaskViewModel.insert(name,desc)
            Navigation.findNavController(it).navigate(R.id.action_addTaskFragment_to_titleFragment)
        }



        return binding.root

    }


}