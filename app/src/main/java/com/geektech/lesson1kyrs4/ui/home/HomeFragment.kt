package com.geektech.lesson1kyrs4.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.geektech.lesson1kyrs4.App
import com.geektech.lesson1kyrs4.R
import com.geektech.lesson1kyrs4.model.Task
import com.geektech.lesson1kyrs4.databinding.FragmentHomeBinding
import com.geektech.lesson1kyrs4.ui.home.adapter.TaskAdapter
import com.geektech.lesson1kyrs4.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::deleteClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //val root: View = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val tasks = App.db.taskDao().getAll()
//        adapter.addTask(tasks)
//        binding.recyclerView.adapter = adapter
//        binding.fab.setOnClickListener {
//            findNavController().navigate(R.id.taskFragment)
//        }

        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }

        private fun setData() {
            val tasks = App.db.taskDao().getAll()
            adapter.addTask(tasks)
        }

        private fun deleteClick(task: Task) {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete?")
            alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, pos: Int) {
                    dialog?.cancel()
                }
            })
            alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, pos: Int) {
                    App.db.taskDao().delete(task)
                    setData()
                }

            })
            alertDialog.create().show()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }



