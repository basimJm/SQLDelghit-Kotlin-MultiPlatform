package com.blackhand.sqldelight.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.jetbrains.spacetutorial.cache.NoteDB
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(NoteDB.Schema, "NotesDb")
    }
}