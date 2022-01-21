package com.sn.mvvmexample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sn.mvvmexample.data.model.TodosItemModel
import io.reactivex.rxjava3.core.Single

@Dao
interface TodosDao {

    @Query("SELECT * FROM todos_table")
    fun getTodosDB(): Single<List<TodosItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todosItemModel: List<TodosItemModel>): Single<List<Long>>

}
