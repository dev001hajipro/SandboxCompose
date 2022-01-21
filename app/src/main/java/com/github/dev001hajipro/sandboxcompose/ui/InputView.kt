package com.github.dev001hajipro.sandboxcompose.ui

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.dev001hajipro.sandboxcompose.database.TodoItem
import com.github.dev001hajipro.sandboxcompose.database.TodoViewModel
import com.github.dev001hajipro.sandboxcompose.database.TodoViewModelFactory

@Composable
fun InputView() {
    // 状態管理
    var text by remember { mutableStateOf("") }

    var text2 by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val todoViewModel: TodoViewModel =
        viewModel(factory = TodoViewModelFactory(context.applicationContext as Application))

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("InputView")
        OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Todo") })
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = {
            addTodoItem(text, todoViewModel)
        }) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Text("追加")
        }
    }
}

private fun addTodoItem(
    text: String,
    todoViewModel: TodoViewModel
) {
    if (text.isNotEmpty()) {
        Log.d("addTodoItem", text)
        todoViewModel.add(TodoItem(name = text))
    }
}

class InputViewModel : ViewModel() {
    private val _todo:MutableLiveData<String> = MutableLiveData("")
    val todo: LiveData<String> = _todo

    fun onChange(v:String) { _todo.value = v }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputView() {
    InputView()
}
