package com.example.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityAddTaskBinding.inflate(layoutInflater)

        // Set the content view to the root of the inflated layout
        setContentView(binding.root)

        // Initialize the database helper
        db = TaskDatabaseHelper(this)

        // Set click listener for the save button
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val content = binding.contentEditText.text.toString().trim()

            // Check if title and content are not empty
            if (title.isNotEmpty() && content.isNotEmpty()) {
                // Create a new Task object
                val task = Task(0, title, content)

                // Insert the task into the database
                db.insertTask(task)

                // Finish the activity
                finish()

                // Show a toast message indicating successful task creation
                Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
            } else {
                // Show a toast message indicating that title or content is empty
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
