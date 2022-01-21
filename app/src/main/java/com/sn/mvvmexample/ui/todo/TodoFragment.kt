package com.sn.mvvmexample.ui.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sn.mvvmexample.util.State
import com.sn.mvvmexample.databinding.TodoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.todo_fragment.*

@AndroidEntryPoint
class TodoFragment : Fragment() {
    private val vm: TodoViewModel by viewModels()
    private val binding by lazy {
        TodoFragmentBinding.inflate(layoutInflater)
    }
    private val todoAdapter by lazy {
        TodoAdapter {
            val action = TodoFragmentDirections.actionTodoToDetail(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@TodoFragment
            viewModel = this@TodoFragment.vm
        }
        initializeRecycler()
        observe()

        binding.srlRefresh.setOnRefreshListener {
            vm.getTodosRemote()
            binding.srlRefresh.isRefreshing = false
        }

    }

    private fun observe() {
        vm.todosLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is State.Loading -> {
                    vm.isLoadingLiveData.value = true
                }
                is State.Success -> {
                    vm.isLoadingLiveData.postValue(false)
                    todoAdapter.setTodos(response.data)
                    vm.insertTodosDB(response.data)
                }
                is State.Error -> {
                    vm.isErrorLiveData.postValue(true)
                    vm.isLoadingLiveData.postValue(false)
                }
            }
        }

        vm.todosLocalLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is State.Loading -> {
                    vm.isLoadingLiveData.value = true
                }
                is State.Success -> {
                    vm.isLoadingLiveData.postValue(false)

                    if (response.data.isEmpty()) {
                        vm.getTodosRemote()
                    } else {
                        todoAdapter.setTodos(response.data)
                    }
                }
                is State.Error -> {
                    vm.isErrorLiveData.postValue(true)
                    vm.isLoadingLiveData.postValue(false)
                }
            }
        }

    }

    private fun initializeRecycler() {
        binding.rcvTodo.apply {
            setHasFixedSize(true)
            adapter = todoAdapter
        }
    }

}