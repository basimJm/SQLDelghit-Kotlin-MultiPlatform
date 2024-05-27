package com.blackhand.sqldelight.android.domain.state

import com.blackhand.sqldelight.domain.model.local.NotesEntity

data class NoteState(
    var noteList: List<NotesEntity>? = null,
    var note: NotesEntity? = null,
    var isLoading: Boolean? = false,
    var successMessage: String? = null,
    var errorMessage: String? = null,
    var searchText: String? = null,
    var isSearchActive: Boolean? = null
)
