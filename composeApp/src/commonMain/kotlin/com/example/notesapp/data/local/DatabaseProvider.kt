package com.example.notesapp.data.local

import app.cash.sqldelight.db.SqlDriver
import com.example.notesapp.db.NotesDatabase

object DatabaseProvider {
    @Volatile
    private var instance: NotesDatabase? = null

    fun getDatabase(driver: SqlDriver): NotesDatabase {
        return instance ?: synchronized(this) {
            instance ?: NotesDatabase(driver).also { instance = it }
        }
    }
}