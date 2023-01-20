package com.geektech.lesson1kyrs4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.geektech.lesson1kyrs4.R
import com.geektech.lesson1kyrs4.model.Task
import com.geektech.lesson1kyrs4.databinding.FragmentHomeBinding
import com.geektech.lesson1kyrs4.ui.home.adapter.TaskAdapter
import com.geektech.lesson1kyrs4.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter:TaskAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter =TaskAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(TaskFragment.RESULT_TASK) { key, bundle ->

            val result = bundle.getSerializable("task") as Task
            //Log.e("ololo","onViewCreated: "+result)
            adapter.addTask(result)
        }

        binding.recyclerView.adapter=adapter
        binding.fab.setOnClickListener{
findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}