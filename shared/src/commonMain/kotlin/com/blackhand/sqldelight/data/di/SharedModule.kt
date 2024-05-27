package com.blackhand.sqldelight.data.di

import com.blackhand.sqldelight.core.util.provideDispatcher
import com.blackhand.sqldelight.data.db.DatabaseHelper
import com.blackhand.sqldelight.data.repo.NoteRepositoryImpl
import com.blackhand.sqldelight.domain.NoteSearchUseCase
import com.blackhand.sqldelight.domain.repo.NotesRepository
import org.koin.core.module.Module
import org.koin.dsl.module


object SharedModule {
    val repositoryModule = module {
        single<NotesRepository> { NoteRepositoryImpl(get(), get()) }
    }
    val useCaseModule = module {
        factory { NoteSearchUseCase() }
    }
    val commonModule = module {
        factory { provideDispatcher() }
    }

    val databaseModule = module {
        factory { DatabaseHelper(get()) }
    }

}

expect val platformModule: Module

fun initKoin(
    repositoryModule: Module = SharedModule.repositoryModule,
    useCaseModule: Module = SharedModule.useCaseModule,
    appModule: Module = module { },
    commonModule: Module = SharedModule.commonModule,
    viewModelModule: Module = module {},
    databaseModule: Module = SharedModule.databaseModule
,    sqlDriverModule:Module = module{}
): List<Module> {
    return listOf(
        appModule,
        viewModelModule,
        repositoryModule,
        useCaseModule,
        commonModule,
        databaseModule,sqlDriverModule
    )
}