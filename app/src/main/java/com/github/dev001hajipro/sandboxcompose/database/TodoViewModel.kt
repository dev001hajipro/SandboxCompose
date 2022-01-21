package com.github.dev001hajipro.sandboxcompose.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<TodoItem>>
    private val repository: TodoRepository

    init {
        val dao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepository(dao)
        readAllData = repository.readAllData
    }

    fun add(x: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(x);
        }
    }

    fun update(x: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) { repository.update(x) }
    }

    fun delete(x: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) { repository.delete(x) }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}

class TodoViewModelFactory(private val application:Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

