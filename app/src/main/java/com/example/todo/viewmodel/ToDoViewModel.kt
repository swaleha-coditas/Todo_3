package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.room.ToDo
import com.example.todo.room.ToDoDatabase
import com.example.todo.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application){

    val allToDos : LiveData<List<ToDo>>
    private val repository : ToDoRepository

    init {
        val dao = ToDoDatabase.getDatabase(application).todoDao()
         repository = ToDoRepository(dao)
        allToDos = repository.allToDos
    }

     fun insertToDo(toDo: ToDo) {
         viewModelScope.launch(Dispatchers.IO) {
             repository.insertToDo(toDo)
         }
    }
    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateToDo(toDo)
        }
    }
    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDo(toDo)
        }
    }


}