package com.example.todo.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo")
 data class ToDo (
     val title : String ,
     val description : String
         ){
     @PrimaryKey(autoGenerate = true)
     var id :Int = 0
 }

