package com.example.todo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

class NewDialogFragment : DialogFragment() {
    interface  NewDialogListener{
        fun onDialogPositiveClick(dialog: DialogFragment,toDo: ToDo , isEdit: Boolean,position: Int?)

        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private var newDialogListener: NewDialogListener? = null
    private var isEdit = false
    private lateinit var viewModel: ToDoViewModel


    companion object {
        fun newInstance(title: Int,description : Int, position: Int?):
                NewDialogFragment {

            val newDialogFragment = NewDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            args.putInt("dialog_description",description)
            if (position != null) {
                args.putInt("item_position", position)
            } else {
                args.putInt("item_position", -1)
            }
            newDialogFragment.arguments = args
            return newDialogFragment
        }
    }
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            viewModel = ViewModelProvider(it).get(ToDoViewModel::class.java)
        }

        val title = arguments?.getInt("dialog_title")
        val description = arguments?.getInt("dialog_description")
        val position = arguments?.getInt("item_position", -1)

        val builder = AlertDialog.Builder(activity)
        if (title != null) {
            builder.setTitle(title)
        }

        newDialogListener = activity as NewDialogListener

        val dialogView =
            activity?.layoutInflater?.inflate(R.layout.add_dialog, null)

        val itemTitle = dialogView?.findViewById<EditText>(R.id.addTitle)
        val itemDescription = dialogView?.findViewById<EditText>(R.id.addDescription)


        if (position != -1) {
            val retrievedItem = viewModel.todo[position!!]
            isEdit = true
            itemTitle?.setText(retrievedItem.title)
            itemDescription?.setText(retrievedItem.description)

        }

        builder.setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val addTitle = itemTitle?.text
                val addDescription = itemDescription?.text


                val todo = ToDo(
                    title = itemTitle?.text.toString(),
                    description = itemDescription?.text.toString()
                )

                newDialogListener?.onDialogPositiveClick(this, todo, isEdit, position)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                newDialogListener?.onDialogNegativeClick(this)
            }
        return builder.create()
    }
}

