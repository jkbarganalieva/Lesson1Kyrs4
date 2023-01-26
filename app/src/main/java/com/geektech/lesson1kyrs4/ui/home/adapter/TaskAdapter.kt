package com.geektech.lesson1kyrs4.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.lesson1kyrs4.model.Task
import com.geektech.lesson1kyrs4.databinding.ItemTaskBinding

class TaskAdapter(private val deleteClick: (Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val data = arrayListOf<Task>()

    fun addTask(task: Task) {
        data.add(0, task)
        //notifyDataSetChanged()
        notifyItemChanged(0)
    }

    fun addTask(list: List<Task>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(
        private val binding:
        ItemTaskBinding
    ) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            binding.itemView.setOnLongClickListener {
                deleteClick(task)
                false

            }
        }
    }
}
