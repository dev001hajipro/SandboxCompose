package com.github.dev001hajipro.sandboxcompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.dev001hajipro.sandboxcompose.database.TodoItem
import com.github.dev001hajipro.sandboxcompose.database.TodoViewModel
import com.github.dev001hajipro.sandboxcompose.database.TodoViewModelFactory
import com.github.dev001hajipro.sandboxcompose.ui.InputView
import com.github.dev001hajipro.sandboxcompose.ui.theme.SandboxComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SandboxComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SandboxComposeTheme {
        HomeView()
    }
}

const val TITLE = "title"

@Composable
fun HomeView() {
    val context = LocalContext.current
    val model: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )
    val items = model.readAllData.observeAsState(listOf()).value

    val username = "john"

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "こんにちは $username",
            style = MaterialTheme.typography.h4,
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Text(
            TITLE,
            style = MaterialTheme.typography.h6,
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        InputView()

        ListTodos(items)

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListTodos(items: List<TodoItem>) {
    LazyColumn {
        items(items) { todo ->
            ListItem(
                text = { Text(text = todo.name) }
            )
        }
    }
}


