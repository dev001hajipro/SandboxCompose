package com.github.dev001hajipro.sandboxcompose.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_todo_list")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name="item_name")
    val name:String,

    @ColumnInfo(name="is_completed")
    var isDone: Boolean = false
)
