package com.example.todo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.OnItemLongClickListener
import com.example.todo.R
import com.example.todo.databinding.RowLayoutBinding
import com.example.todo.room.ToDo

class ToDoAdapter( val context: Context,private val onItemLongClickListener: OnItemLongClickListener) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private var allToDos = ArrayList<ToDo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding:RowLayoutBinding=DataBindingUtil.inflate(
            layoutInflater, R.layout.row_layout,parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = allToDos[position]
        holder.bind(allToDos[position])
        holder.binding.rowLayout.setOnLongClickListener {
            onItemLongClickListener.onItemLongClickListener(allToDos[holder.absoluteAdapterPosition],it.rootView)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return allToDos.size
    }
    fun updateItem(newList: List<ToDo>){
        allToDos.clear()
        allToDos.addAll(newList)
        notifyDataSetChanged()

    }

     inner class ToDoViewHolder(val binding: RowLayoutBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(todo : ToDo){
           binding.itemTitle.text = todo.title
           binding.itemDescription.text = todo.description

        }
     }
}