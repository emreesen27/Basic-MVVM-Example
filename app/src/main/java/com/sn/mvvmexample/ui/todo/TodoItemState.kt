package com.sn.mvvmexample.ui.todo


import com.sn.mvvmexample.R
import com.sn.mvvmexample.data.model.TodosItemModel

data class TodoItemState(
    val item: TodosItemModel
) {
    fun id() = item.id ?: "id is null"
    fun title() = item.title ?: "title is null"
    fun userId() = item.userId ?: "userId is null"
    fun completed() = if (item.completed == false) R.drawable.ic_false else R.drawable.ic_true
}