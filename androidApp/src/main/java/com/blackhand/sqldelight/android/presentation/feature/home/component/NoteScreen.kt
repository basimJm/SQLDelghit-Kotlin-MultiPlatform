package com.blackhand.sqldelight.android.presentation.feature.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.blackhand.sqldelight.android.presentation.feature.home.viewmodel.HomeViewModel
import com.blackhand.sqldelight.domain.model.local.NotesEntity

@Composable
fun NoteScreen(homeViewModel: HomeViewModel) {
    val data = homeViewModel.state.collectAsState()
    LaunchedEffect(true) {
        homeViewModel.fetchALlNotes()
    }
    Scaffold(floatingActionButton = { AddNoteFAB({}) }) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.White)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                HidableSearchTextField(
                    isSearchActive = data.value.isSearchActive == true,
                    onTextChanged = homeViewModel::onSearchTextChanged,
                    text = data.value.searchText.toString(),
                    openSearch = homeViewModel::onToggleSearch,
                    closeSearch = homeViewModel::onToggleSearch,
                )
                this@Column.AnimatedVisibility(
                    visible = data.value.isSearchActive == false,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "All notes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, color = Color.Black
                    )
                }
            }
            NotesList(notes = data.value.noteList, viewModel = homeViewModel, data.value.isLoading)
        }
    }
}

@Composable
fun ColumnScope.NotesList(
    notes: List<NotesEntity>?,
    viewModel: HomeViewModel,
    isLoading: Boolean?
) {
    AnimatedVisibility(visible = isLoading == true) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            notes?.let {
                items(notes) {
                    NoteItem(
                        note = it,
                        deleteNote = viewModel::deleteNote,
                        onNoteClicked = { /*TODO*/ },
                        background = Color.Gray
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = isLoading == true) {
        Box(modifier = Modifier.weight(1f)) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    }
}

@Composable
fun AddNoteFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }, shape = RoundedCornerShape(26), containerColor = Color.DarkGray
    ) {
        Icon(Icons.Filled.Add, "Add Note")
    }
}