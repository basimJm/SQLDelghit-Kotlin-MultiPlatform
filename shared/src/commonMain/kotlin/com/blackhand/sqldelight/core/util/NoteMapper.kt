package com.blackhand.sqldelight.core.util

import com.blackhand.sqldelight.domain.model.local.NotesEntity
import com.jetbrains.spacetutorial.cache.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): NotesEntity {
    return NotesEntity(
        id = id,
        title = title,
        content = content,
        colorHex = colotHex,
        created = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}