package com.example.todo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.room.ToDo


@Dao
interface ToDoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getAllToDos() : LiveData<List<ToDo>>

}