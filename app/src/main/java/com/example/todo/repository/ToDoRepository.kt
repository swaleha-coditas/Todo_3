package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.room.ToDo
import com.example.todo.room.ToDoDAO

class ToDoRepository(private val todoDAO: ToDoDAO) {

   val allToDos: LiveData<List<ToDo>> = todoDAO.getAllToDos()

    suspend fun insertToDo(toDo: ToDo){
        todoDAO.insertToDo(toDo)
    }
    suspend fun updateToDo(toDo: ToDo){
        todoDAO.updateToDo(toDo)
    }
    suspend fun deleteToDo(toDo: ToDo){
        todoDAO.deleteToDo(toDo)
    }
}