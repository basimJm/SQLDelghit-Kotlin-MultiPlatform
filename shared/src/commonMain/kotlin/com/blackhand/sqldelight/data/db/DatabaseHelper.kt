package com.blackhand.sqldelight.data.db

import app.cash.sqldelight.db.SqlDriver
import com.jetbrains.spacetutorial.cache.NoteDBQueries

class DatabaseHelper(sqlDriver: SqlDriver) {
     val noteQueries = NoteDBQueries(sqlDriver)
}