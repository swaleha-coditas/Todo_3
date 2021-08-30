package com.example.todo.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo")
 data class ToDo (
    var title : String,
    var description : String
         ){
     @PrimaryKey(autoGenerate = true)
     var id :Int = 0
 }

