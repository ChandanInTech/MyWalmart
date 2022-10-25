package com.chandan.mywalmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandan.mywalmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoAdapter = TodoAdapter(viewModel::removeItem, viewModel::editNote)

        viewModel.todoItemList.observe(this) {
            todoAdapter.submitList(it)
        }

        binding.todoListRv.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.fab.setOnClickListener { view ->
            viewModel.addTodoItem(binding.editTextTextPersonName.text.toString())
            binding.editTextTextPersonName.text.clear()
        }
    }
}