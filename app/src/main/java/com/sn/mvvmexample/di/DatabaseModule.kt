package com.sn.mvvmexample.di

import android.content.Context
import androidx.room.Room
import com.sn.mvvmexample.data.local.TodosDao
import com.sn.mvvmexample.data.local.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Local Database
 * Reference: https://developer.android.com/training/data-storage/room
 */

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideChannelDao(todosDatabase: TodosDatabase): TodosDao {
        return todosDatabase.todosDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TodosDatabase {
        return Room.databaseBuilder(
            appContext,
            TodosDatabase::class.java,
            "todos"
        ).allowMainThreadQueries().build()
    }


}