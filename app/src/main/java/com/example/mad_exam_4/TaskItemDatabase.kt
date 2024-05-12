package com.example.mad_exam_4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskItemDatabase:RoomDatabase() {

    // Abstract method to retrieve TaskItemDao
    abstract fun taskItemDao(): TaskItemDao

    // Companion object to provide a singleton instance of the database
    companion object{

        @Volatile
        private var INSTANCE: TaskItemDatabase? = null

        // Function to get an instance of TaskItemDatabase
        fun getDatabase(context: Context): TaskItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemDatabase::class.java,
                    "task_item_database"
                ).build()

                INSTANCE = instance
                instance
            }

        }
    }
}