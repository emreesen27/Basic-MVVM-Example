package com.sn.mvvmexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sn.mvvmexample.data.model.TodosItemModel

@Database(entities = [TodosItemModel::class], version = 1, exportSchema = false)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todosDao(): TodosDao
}