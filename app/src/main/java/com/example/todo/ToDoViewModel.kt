package com.example.todo

import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    var todo : ArrayList<ToDo> = ArrayList()
    fun removeItem(position: Int) {
        todo.removeAt(position)
    }

    fun updateItem(position: Int, updatedItem: ToDo) {
        todo[position] = updatedItem
    }
    }