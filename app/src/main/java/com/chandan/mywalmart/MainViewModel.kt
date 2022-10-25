package com.chandan.mywalmart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    private val _todoItemList = MutableLiveData(ArrayList<TodoItem>())
    val todoItemList = _todoItemList as LiveData<ArrayList<TodoItem>>

    fun addTodoItem(text: String) {
        _todoItemList.value?.add(TodoItem(UUID.randomUUID(), text, false))
        _todoItemList.postValue(_todoItemList.value)
    }

    fun removeItem(id: UUID) {
        _todoItemList.value?.removeIf { it.id == id }
        _todoItemList.postValue(_todoItemList.value)
    }

    fun editNote(id: UUID, newText: String) {
        _todoItemList.value?.firstOrNull { it.id == id }?.text = newText
        _todoItemList.postValue(_todoItemList.value)
    }
}