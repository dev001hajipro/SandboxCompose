package com.github.dev001hajipro.sandboxcompose.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {
    private lateinit var dao: TodoDao
    private lateinit var db: TodoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.todoDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTodo() = runBlocking {
        val item = TodoItem(itemId = 1, name = "Dummy", isDone = false)
        dao.insert(item)
        val x = dao.getById(1)
        assertEquals(x?.itemId, 1L)
    }

    @Test
    @Throws(Exception::class)
    fun updateAndGetTodo() = runBlocking {
        val item = TodoItem(itemId = 1, name = "Dummy", isDone = false)
        dao.insert(item)
        val x = dao.getById(1)
        if (x != null) {
            x.isDone = true
            dao.update(x)

            assertEquals(dao.getById(1)?.isDone, true)
        }

    }
}
