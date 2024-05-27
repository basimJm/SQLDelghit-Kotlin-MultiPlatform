package com.blackhand.sqldelight.android.presentation.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackhand.sqldelight.core.common.DateTimeUtils
import com.blackhand.sqldelight.core.common.DateTimeUtils.now
import com.blackhand.sqldelight.domain.model.local.NotesEntity

@Composable
fun NoteItem(
    note: NotesEntity?,
    deleteNote: (Long) -> Unit,
    onNoteClicked: () -> Unit,
    background: Color
) {
    val formatDate = remember(note?.created) {
        DateTimeUtils.formatNoteDate(note?.created ?: now())
    }
    Column(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        .background(color = background)
        .clickable { onNoteClicked() }, verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(text = note?.title.toString(), color = Color.Black, fontSize = 16.sp)
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete Note", tint = Color.Black,
                modifier = Modifier.clickable { deleteNote(note?.id ?: 0) })
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                text = note?.content.toString(),
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            Text(
                text = formatDate,
                color = Color.LightGray,
                fontSize = 8.sp,
            )
        }
    }
}