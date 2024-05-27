package com.blackhand.sqldelight.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jetbrains.spacetutorial.cache.NoteDB
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(NoteDB.Schema, get(), "NotesDb")
    }
}