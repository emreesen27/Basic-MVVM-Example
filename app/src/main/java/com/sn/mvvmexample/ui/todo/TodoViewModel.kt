package com.sn.mvvmexample.ui.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sn.mvvmexample.util.State
import com.sn.mvvmexample.data.model.TodosItemModel
import com.sn.mvvmexample.data.local.LocalDataSource
import com.sn.mvvmexample.data.remote.RemoteDateSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val remoteDateSource: RemoteDateSource,
    private val localDataSource: LocalDataSource
) :
    ViewModel() {

    private val _todosLiveData: MutableLiveData<State<List<TodosItemModel>>> = MutableLiveData()
    val todosLiveData: LiveData<State<List<TodosItemModel>>> get() = _todosLiveData

    private val _todosLocalLiveData: MutableLiveData<State<List<TodosItemModel>>> =
        MutableLiveData()
    val todosLocalLiveData: LiveData<State<List<TodosItemModel>>> get() = _todosLocalLiveData

    val isLoadingLiveData = MutableLiveData<Boolean>().apply { value = false }
    val isErrorLiveData = MutableLiveData<Boolean>().apply { value = false }


    init {
        getTodosDB()
    }

    private fun getTodosDB() {
        localDataSource.getTodosDB().observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                _todosLocalLiveData.postValue(response)
            }
    }

    fun insertTodosDB(todos: List<TodosItemModel>) {
        localDataSource.insertTodosDB(todos).observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                Log.d("MVVMExample", "ids${response}")
            }
    }

    fun getTodosRemote() {
        remoteDateSource
            .getTodosRemote()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                _todosLiveData.postValue(response)
            }
    }

}