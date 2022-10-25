package com.chandan.mywalmart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chandan.mywalmart.databinding.TodoItemBinding
import java.util.UUID

class TodoAdapter(val onCheckBoxClick: (UUID) -> Unit, val editNote: (UUID, String) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private lateinit var binding: TodoItemBinding

    private val differCallBack = object : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.isChecked == newItem.isChecked && oldItem.text == newItem.text
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    fun submitList(list: ArrayList<TodoItem>) {
        differ.submitList(ArrayList<TodoItem>().apply {
            addAll(list)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: TodoItem) {
            binding.apply {
                textView.text = item.text
                checkbox.isChecked = item.isChecked

                checkbox.setOnCheckedChangeListener { compoundButton, b ->
                    onCheckBoxClick.invoke(item.id)
                }

                edit.setOnClickListener {
                    editNote.invoke(item.id, "new edited text")
                }
            }
        }
    }
}