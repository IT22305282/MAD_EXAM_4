package com.example.mad_exam_4

interface TaskItemClickListener {
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
    fun incompleteTaskItem(taskItem: TaskItem)

    fun deleteTaskItem(taskItem: TaskItem)

}