package com.geektech.lesson1kyrs4.ui.task

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.geektech.lesson1kyrs4.App
import com.geektech.lesson1kyrs4.R
import com.geektech.lesson1kyrs4.model.Task
import com.geektech.lesson1kyrs4.databinding.FragmentTaskBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private val db = Firebase.firestore
    private lateinit var navArgs: TaskFragmentArgs
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            task = navArgs.task
        }

        if (task != null) {
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
            binding.btnSave.text = getString(R.string.update)
        } else {
            binding.btnSave.text = getString(R.string.save)
        }

        binding.btnSave.setOnClickListener {
            if (task != null) {
                onUpdate()
            } else onSave()
        }
    }

    private fun onUpdate() {
        task?.title=binding.etTitle.text.toString()
        task?.desc=binding.etDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun onSave() {
        val task = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()
    }

    private fun putTask(task: Task) {
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            db.collection(it).add(task).addOnSuccessListener {
                Log.e("ololo", "onSave: success!")
            }.addOnFailureListener {
                Log.e("ololo", "onSave: " + it.message)
            }
        }
    }

    companion object {
        const val RESULT_TASK = "result.task"
    }

}