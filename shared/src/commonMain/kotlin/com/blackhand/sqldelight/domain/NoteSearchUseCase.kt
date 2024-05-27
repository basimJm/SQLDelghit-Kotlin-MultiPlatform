package com.blackhand.sqldelight.domain

import com.blackhand.sqldelight.core.common.DateTimeUtils
import com.blackhand.sqldelight.domain.model.local.NotesEntity

class NoteSearchUseCase {
     fun execute(note: List<NotesEntity>?, query: String): List<NotesEntity>? {
        return if (query.isBlank()) {
            note
        } else {
            note?.filter {
                it.title?.contains(query) == true || it.content?.contains(query) == true
            }?.sortedBy { DateTimeUtils.toEpochMillis(it.created) }
        }
    }
}