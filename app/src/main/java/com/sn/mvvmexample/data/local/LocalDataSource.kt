package com.sn.mvvmexample.data.local

import com.sn.mvvmexample.data.model.TodosItemModel
import com.sn.mvvmexample.util.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val db: TodosDao) {

    fun getTodosDB(): Observable<State<List<TodosItemModel>>> =
        Observable.create { emitter ->
            emitter.onNext(State.Loading)
            db.getTodosDB().subscribeOn(Schedulers.io()).subscribe(
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

    fun insertTodosDB(todos: List<TodosItemModel>): Observable<State<List<Long>>> =
        Observable.create { emitter ->
            emitter.onNext(State.Loading)
            db.insertAll(todos).subscribeOn(Schedulers.io()).subscribe(
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