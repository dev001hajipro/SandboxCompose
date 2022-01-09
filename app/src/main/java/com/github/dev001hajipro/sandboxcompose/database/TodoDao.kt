package com.github.dev001hajipro.sandboxcompose.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("select * from my_todo_list")
    fun getAll(): LiveData<List<TodoItem>>

    @Query("select * from my_todo_list where itemId = :id")
    fun getById(id: Int): TodoItem?

    @Insert
    suspend fun insert(item:TodoItem)

    @Update
    suspend fun update(item:TodoItem)

    @Delete
    suspend fun delete(item:TodoItem)

    @Query("delete from my_todo_list")
    suspend fun deleteAllTodos()
}
