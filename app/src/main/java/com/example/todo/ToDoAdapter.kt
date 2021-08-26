package com.example.todo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.RowLayoutBinding

class ToDoAdapter(private var todos: List<ToDo>,private val onItemLongClickListener: OnItemLongClickListener) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding:RowLayoutBinding=DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.row_layout,parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
       val todo = todos[position]
        holder.bind(todos[position])
         holder.binding.rowLayout.setOnLongClickListener{
            onItemLongClickListener.onItemLongClickListener(position,it)
            return@setOnLongClickListener true



        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }

     inner class ToDoViewHolder(val binding: RowLayoutBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(todo : ToDo){
           binding.apply{
               title = todo.title
               description = todo.description
}
}
}

}