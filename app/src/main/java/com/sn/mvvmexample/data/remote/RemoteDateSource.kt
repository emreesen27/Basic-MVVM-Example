package com.sn.mvvmexample.data.remote

import com.sn.mvvmexample.util.State
import com.sn.mvvmexample.data.model.TodosItemModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDateSource @Inject constructor(private val api: TodoService) {

    fun getTodosRemote(): Observable<State<List<TodosItemModel>>> =
        Observable.create { emitter ->
            emitter.onNext(State.Loading)
            api.getTodosRemote().subscribeOn(Schedulers.io()).subscribe(
                { response ->
                    emitter.onNext(State.Success(response))
                    emitter.onComplete()
                },
                { throwable ->
                    emitter.onNext(State.Error(throwable))
                    emitter.onComplete()
                }
            )
        }
}