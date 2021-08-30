package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.adapter.ToDoAdapter
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.room.ToDo
import com.example.todo.viewmodel.ToDoViewModel


class MainActivity : AppCompatActivity(), OnItemLongClickListener{

    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val todoAdapter = ToDoAdapter(this,this)
        binding.recyclerView.adapter = todoAdapter
        binding.addButton.setOnClickListener { addInfo() }


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ToDoViewModel::class.java)
        viewModel.allToDos.observe(this, Observer { list->
            list?.let {
                todoAdapter.updateItem(it)

            }

        })

        //Dummy Data
        //  val todos = ArrayList<ToDo>()
        //  todos.add(ToDo("Item 1", "Go for a walk"))
        // todos.add(ToDo("Item 2", "Have coffee"))
        //   todos.add(ToDo("Item 3", "Talk to friends"))
        //  todos.add(ToDo("Item 4", "Buy stuffs"))

    }

    override fun onItemLongClickListener(toDo : ToDo, view : View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.show_menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.update_item -> {
                    val v = LayoutInflater.from(this).inflate(R.layout.add_dialog,null)
                    val addTitle = v.findViewById<TextView>(R.id.addTitle)
                    val addDescription = v.findViewById<TextView>(R.id.addDescription)
                    AlertDialog.Builder(this)
                        .setView(v)
                        .setPositiveButton("Ok"){
                                dialog,_->
                            toDo.title = addTitle.text.toString()
                            toDo.description = addDescription.text.toString()
                            viewModel.updateToDo(toDo)
                            Toast.makeText(this,"User Information is Edited",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                        }
                        .setNegativeButton("Cancel"){
                                dialog,_->
                            dialog.dismiss()

                        }
                        .create()
                        .show()

                }
                R.id.delete_item -> {
                    AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setMessage("Are you sure delete this Information")
                        .setPositiveButton("Yes"){
                                dialog,_->
                                    viewModel.deleteToDo(toDo)
                                    Toast.makeText(this,"Deleted this Information",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No"){
                                dialog,_->
                            dialog.dismiss()
                        }
                        .create()
                        .show()

                }
            }

            true

        }

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
            if(addTitleText.isNotEmpty() || addDescriptionText.isNotEmpty()) {
                viewModel.insertToDo(ToDo(addTitleText, addDescriptionText))

                Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        .create()
        .show()
    }
}


