package com.sn.mvvmexample.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todos_table")
@Parcelize
data class TodosItemModel(
	@PrimaryKey
	@ColumnInfo(name = "todo_id")
	val id: Int? = null,
	@ColumnInfo(name = "completed")
	val completed: Boolean? = null,
	@ColumnInfo(name = "title")
	val title: String? = null,
	@ColumnInfo(name = "user_id")
	val userId: Int? = null
): Parcelable

