package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnItemLongClickListener {
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var todos: ArrayList<ToDo>
    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Dummy Data
        //  val todos = ArrayList<ToDo>()
        //  todos.add(ToDo("Item 1", "Go for a walk"))
        // todos.add(ToDo("Item 2", "Have coffee"))
        //   todos.add(ToDo("Item 3", "Talk to friends"))
        //  todos.add(ToDo("Item 4", "Buy stuffs"))
        todos = ArrayList()
        addButton = findViewById(R.id.addButton)
        todoAdapter = ToDoAdapter(todos,this)
        addButton.setOnClickListener { addInfo() }


        val adapter = ToDoAdapter(todos,this)


        recyclerView.adapter = adapter
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_dialog, null)

        val addTitle = v.findViewById<TextView>(R.id.addTitle)
        val addDescription = v.findViewById<TextView>(R.id.addDescription)
        val addDialog = AlertDialog.Builder(this)

        with(addDialog) {

            setView(v)
        }
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val addTitleText = addTitle.text.toString()
            val addDescriptionText = addDescription.text.toString()
            todos.add(ToDo("Title: $addTitleText", "Description. : $addDescriptionText"))
            Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    override fun onItemLongClickListener(position: Int) {
        Toast.makeText(this, "Item Clicked", Toast.LENGTH_SHORT).show()
    }


}