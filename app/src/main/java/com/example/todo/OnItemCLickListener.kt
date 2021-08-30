package com.example.todo

import android.view.View
import com.example.todo.room.ToDo


interface OnItemLongClickListener {

    fun onItemLongClickListener(toDo: ToDo, view: View)

}