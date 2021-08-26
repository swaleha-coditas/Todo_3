package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnItemLongClickListener, NewDialogFragment.NewDialogListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: Button
    private lateinit var todos: ArrayList<ToDo>
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var viewModel: ToDoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.recyclerView.adapter = ToDoAdapter(viewModel.todo, this)

        //Dummy Data
        //  val todos = ArrayList<ToDo>()
        //  todos.add(ToDo("Item 1", "Go for a walk"))
        // todos.add(ToDo("Item 2", "Have coffee"))
        //   todos.add(ToDo("Item 3", "Talk to friends"))
        //  todos.add(ToDo("Item 4", "Buy stuffs"))
        todos = ArrayList()
        addButton = findViewById(R.id.addButton)
        todoAdapter = ToDoAdapter(todos, this)
        binding.addButton.setOnClickListener { addInfo() }


    }


    override fun onItemLongClickListener(position: Int, view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.show_menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.update_item -> {
                     fun updateItem(position: Int) {
                         Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                        val newFragment = NewDialogFragment.newInstance(R.string.add_title,R.string.add_description,position)
                        newFragment.show(supportFragmentManager, "newItem")
                    }

                }
                R.id.delete_item -> {

                    AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setMessage("Are you sure delete this Information")
                        .setPositiveButton("Yes") { dialog, _ ->
                            todos.removeAt(position)
                            todoAdapter.notifyItemRemoved(position)
                            Toast.makeText(this, "Deleted this Information", Toast.LENGTH_SHORT)
                                .show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog, _ ->
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
        val newFragment = NewDialogFragment.newInstance(R.string.add_title,R.string.add_description, null)
        newFragment.show(supportFragmentManager, "newItem")
    }

    override fun onDialogPositiveClick(
        dialog: DialogFragment,
        toDo: ToDo,
        isEdit: Boolean,
        position: Int?
    ) {
        if (!isEdit) {
            viewModel.todo.add(toDo)
        } else {
            viewModel.updateItem(position!!, toDo)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
    }


//   private fun addInfo() {
//       val inflater = LayoutInflater.from(this)
//        val v = inflater.inflate(R.layout.add_dialog, null)
//        val addTitle = v.findViewById<TextView>(R.id.addTitle)
//        val addDescription = v.findViewById<TextView>(R.id.addDescription)
//        val addDialog = AlertDialog.Builder(this)
//
//        with(addDialog) {
//
//            setView(v)
//        }
//        addDialog.setPositiveButton("Ok") { dialog, _ ->
//            val addTitleText = addTitle.text.toString()
//            val addDescriptionText = addDescription.text.toString()
//            todos.add(ToDo("Title: $addTitleText", "Description. : $addDescriptionText"))
//            todoAdapter.notifyDataSetChanged()
//            Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
//            dialog.dismiss()
//        }
//        addDialog.setNegativeButton("Cancel") { dialog, _ ->
//            dialog.dismiss()
//            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
//        }
//        addDialog.create()
//        addDialog.show()
//    }
}


