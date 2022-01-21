package com.sn.mvvmexample.util

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    data class Success<out T : Any>(val data: T) : State<T>()
    data class Error(val throwable: Throwable) : State<Nothing>()
}
