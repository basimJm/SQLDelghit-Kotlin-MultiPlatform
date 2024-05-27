package com.blackhand.sqldelight.domain.model.local

import kotlinx.datetime.LocalDateTime

data class NotesEntity(
    var id: Long? = null,
    var title: String? = null,
    var content: String? = null,
    var colorHex: Long? = null,
    var created: LocalDateTime? =null
)