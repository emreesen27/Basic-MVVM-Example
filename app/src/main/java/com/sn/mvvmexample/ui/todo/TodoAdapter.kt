package com.sn.mvvmexample.ui.todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sn.mvvmexample.data.model.TodosItemModel
import com.sn.mvvmexample.databinding.ItemTodoBinding

class TodoAdapter(private val listener: (TodosItemModel) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.IconSetViewHolder>() {

    private var todos = mutableListOf<TodosItemModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTodos(list: List<TodosItemModel>) {
        this.todos.apply {
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconSetViewHolder =
        IconSetViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: IconSetViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    class IconSetViewHolder(
        private val binding: ItemTodoBinding,
        private val listener: (TodosItemModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodosItemModel) {
            binding.apply {
                itemViewState = TodoItemState(item)
                executePendingBindings()
            }
            binding.cvContainer.setOnClickListener { listener(item) }

        }
    }
}