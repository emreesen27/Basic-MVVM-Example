package com.sn.mvvmexample.data.remote

import com.sn.mvvmexample.data.model.TodosItemModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface TodoService {
    @GET("/todos")
    fun getTodosRemote(): Single<List<TodosItemModel>>
}