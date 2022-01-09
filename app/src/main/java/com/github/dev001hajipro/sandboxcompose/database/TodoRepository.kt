package com.github.dev001hajipro.sandboxcompose.database

import androidx.lifecycle.LiveData

class TodoRepository(private val dao: TodoDao) {
    val readAllData: LiveData<List<TodoItem>> = dao.getAll()
    suspend fun add(x: TodoItem) {
        dao.insert(x)
    }
    suspend fun update(x: TodoItem) {
        dao.update(x)
    }
    suspend fun delete(x: TodoItem) {
        dao.delete(x)
    }
    suspend fun deleteAll(x: TodoItem) {
        dao.deleteAllTodos()
    }
}
