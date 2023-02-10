package com.geektech.lesson1kyrs4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.lesson1kyrs4.App
import com.geektech.lesson1kyrs4.R
import com.geektech.lesson1kyrs4.model.Task
import com.geektech.lesson1kyrs4.databinding.FragmentHomeBinding
import com.geektech.lesson1kyrs4.ui.home.adapter.TaskAdapter
import com.geektech.lesson1kyrs4.utils.isOnline
import com.geektech.lesson1kyrs4.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isOnline()){
          val tasks=getTasks()
        }else{
            setData()
        }
        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun getTasks(): ArrayList<Task> {
        val uid=FirebaseAuth.getInstance().currentUser?.uid
        val tasks= arrayListOf<Task>()
        if (uid!=null){
            db.collection(uid).get().addOnSuccessListener {
               //здесь может быть проблема !!!!
                val data=it.toObjects(Task::class.java)
                tasks.addAll(data)
                Log.e("ololo", "getTasks: $data")
            }.addOnFailureListener {}
        }
        return tasks
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTask(tasks)
    }

    private fun deleteClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Delete?")
        alertDialog.setNegativeButton(
            "No"
        ) { dialog, pos -> dialog?.cancel() }
        alertDialog.setPositiveButton(
            "Yes"
        ) { dialog, pos ->
            App.db.taskDao().delete(task)
            setData()
            showToast("Удалено! ")
        }
        alertDialog.create().show()
    }

    private fun onClick(task: Task ){
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





