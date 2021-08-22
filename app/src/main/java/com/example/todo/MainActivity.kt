package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Dummy Data
        val todos = ArrayList<ToDo>()
        todos.add(ToDo("Item 1", "Go for a walk"))
        todos.add(ToDo("Item 2", "Have coffee"))
        todos.add(ToDo("Item 3", "Talk to friends"))
        todos.add(ToDo("Item 4", "Buy stuffs"))


        val adapter = ToDoAdapter(todos)


        recyclerView.adapter = adapter
    }
}