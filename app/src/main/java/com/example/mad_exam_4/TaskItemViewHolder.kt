package com.example.mad_exam_4

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_exam_4.databinding.FragmentNewTaskSheetBinding
import com.example.mad_exam_4.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
):RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTaskItem(taskItem: TaskItem){

        binding.name.text = taskItem.name

        // Check if the task item is completed
        if (taskItem.isCompleted()) {
            setCompletedState()
        } else {
            setIncompleteState()
        }

        // Set click listener for the complete button
        binding.completeButton.setOnClickListener {
            // Toggle completion state of the task item
            if (taskItem.isCompleted()) {
                // If completed, mark as incomplete
                clickListener.incompleteTaskItem(taskItem)
                setIncompleteState()
            } else {
                // If incomplete, mark as completed
                clickListener.completeTaskItem(taskItem)
                setCompletedState()
            }
        }

        binding.taskCellContainer.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

        binding.deleteButton.setOnClickListener {
            clickListener.deleteTaskItem(taskItem)
        }

        if (taskItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            binding.dueTime.text = ""
    }

    private fun setCompletedState() {
        binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        // Change the image for complete button when task is completed
        binding.completeButton.setImageResource(R.drawable.check_box_checked)
    }

    private fun setIncompleteState() {
        binding.name.paintFlags = 0 // Remove the strike-through effect
        binding.dueTime.paintFlags = 0 // Remove the strike-through effect
        // Change the image for complete button when task is incomplete
        binding.completeButton.setImageResource(R.drawable.check_box_outline)
    }
}