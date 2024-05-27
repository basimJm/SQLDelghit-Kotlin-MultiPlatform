package com.blackhand.sqldelight.data.repo

import com.blackhand.sqldelight.core.common.DateTimeUtils
import com.blackhand.sqldelight.core.util.CommonUiState
import com.blackhand.sqldelight.core.util.Dispatcher
import com.blackhand.sqldelight.core.util.toNote
import com.blackhand.sqldelight.data.db.DatabaseHelper
import com.blackhand.sqldelight.domain.model.local.NotesEntity
import com.blackhand.sqldelight.domain.repo.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent

class NoteRepositoryImpl(
    private val databaseHelper: DatabaseHelper,
    private val dispatcher: Dispatcher
) : NotesRepository, KoinComponent {
   private val noteQueries = databaseHelper.noteQueries

    override fun getAllNotes(): Flow<CommonUiState<List<NotesEntity>>> {
        return flow {
            emit(CommonUiState.Loading(true))
            val data = noteQueries.getAllNotes().executeAsList().map { it.toNote() }
            emit(CommonUiState.Success(data, "Success"))
        }.flowOn(dispatcher.io).catch { emit(CommonUiState.Error(errorMessage = it.message)) }
            .flowOn(dispatcher.io)
    }

    override fun getNoteById(id: Long): Flow<CommonUiState<NotesEntity?>> {
        return flow<CommonUiState<NotesEntity?>> {
            emit(CommonUiState.Loading(true))
            val data = noteQueries.getNoteById(id).executeAsOneOrNull()?.toNote()
            if (data != null) {
                emit(CommonUiState.Success(data = data, "Success"))
            }
        }.flowOn(dispatcher.io).catch { emit(CommonUiState.Error(errorMessage = it.message)) }
            .flowOn(dispatcher.io)
    }


    override fun insertNote(note: NotesEntity?): Flow<CommonUiState<Unit?>> {
        return flow {
            emit(CommonUiState.Loading(true))
            val data = noteQueries.addNote(
                id = note?.id,
                title = note?.title.toString(),
                content = note?.content.toString(),
                colotHex = note?.colorHex ?: 0,
                DateTimeUtils.toEpochMillis(note?.created)
            )
            emit(CommonUiState.Success(data, "Success"))
        }
    }


    override fun deleteNote(id: Long): Flow<CommonUiState<Unit?>> {
        return flow<CommonUiState<Unit?>> {
            emit(CommonUiState.Loading(true))
            val data = noteQueries.deleteNote(id)
            emit(CommonUiState.Success(data = data, "success"))
        }.flowOn(dispatcher.io).catch { emit(CommonUiState.Error(errorMessage = it.message)) }
            .flowOn(dispatcher.io)
    }
}
