package com.blackhand.sqldelight.domain.repo

import com.blackhand.sqldelight.core.util.CommonUiState
import com.blackhand.sqldelight.domain.model.local.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getAllNotes(): Flow<CommonUiState<List<NotesEntity>>>
    fun getNoteById(id: Long): Flow<CommonUiState<NotesEntity?>>
    fun deleteNote(id: Long): Flow<CommonUiState<Unit?>>
    fun insertNote(note: NotesEntity?): Flow<CommonUiState<Unit?>>
}