package com.example.todo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private var todos: List<ToDo>,private val onItemLongClickListener: OnItemLongClickListener ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
       val todo = todos[position]
        holder.itemTitle.text = todo.title
        holder.itemDescription.text = todo.description
        holder.itemView.setOnLongClickListener{
            onItemLongClickListener.onItemLongClickListener(position)
            return@setOnLongClickListener true

        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
     inner class ToDoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         var itemTitle : TextView = itemView.findViewById(R.id.itemTitle)
         var itemDescription : TextView = itemView.findViewById(R.id.itemDescription)

     }

}