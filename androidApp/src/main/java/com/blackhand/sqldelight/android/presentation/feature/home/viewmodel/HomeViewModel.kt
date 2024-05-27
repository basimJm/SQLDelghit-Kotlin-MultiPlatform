package com.blackhand.sqldelight.android.presentation.feature.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.sqldelight.android.domain.state.NoteState
import com.blackhand.sqldelight.core.common.DateTimeUtils
import com.blackhand.sqldelight.core.util.CommonUiState
import com.blackhand.sqldelight.domain.NoteSearchUseCase
import com.blackhand.sqldelight.domain.model.local.NotesEntity
import com.blackhand.sqldelight.domain.repo.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random


class HomeViewModel(
    private val searchUseCase: NoteSearchUseCase,
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val notesList = savedStateHandle.getStateFlow("noteList", emptyList<NotesEntity>())
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val note = savedStateHandle.getStateFlow("note", NotesEntity())
    private val isLoading = savedStateHandle.getStateFlow("isLoading", false)


    val state =
        combine(
            notesList,
            isSearchActive,
            searchText,
            note,
            isLoading
        ) { notesList, isSearchActive, searchText, note, isLoading ->
            NoteState(
                noteList = searchUseCase.execute(notesList, searchText),
                isSearchActive = isSearchActive,
                searchText = searchText, note = note, isLoading = isLoading


            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), NoteState())

    fun fetchALlNotes() {
        notesRepository.getAllNotes().onEach { note ->
            when (note) {
                is CommonUiState.Success -> {
                    savedStateHandle["noteList"] = note.data
                }

                is CommonUiState.Loading -> {
                    savedStateHandle["isLoading"] = note.isLoading
                }

                is CommonUiState.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun onSearchTextChanged(textString: String) {
        savedStateHandle["searchText"] = textString
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            fetchALlNotes()
        }
    }

    fun deleteNote(id: Long) {
        notesRepository.deleteNote(id).onEach { note ->
            when (note) {
                is CommonUiState.Success -> {
                    savedStateHandle["noteList"] = note.successMessage
                }

                is CommonUiState.Loading -> {
                    savedStateHandle["isLoading"] = note.isLoading
                }

                is CommonUiState.Error -> {}
            }
            fetchALlNotes()
        }.launchIn(viewModelScope)
    }

    fun getNoteById(id: Long) {
        notesRepository.getNoteById(id).onEach { note ->
            when (note) {
                is CommonUiState.Success -> {}

                is CommonUiState.Loading -> {
                    savedStateHandle["isLoading"] = note.isLoading
                }

                is CommonUiState.Error -> {}
            }
            fetchALlNotes()
        }
    }
}