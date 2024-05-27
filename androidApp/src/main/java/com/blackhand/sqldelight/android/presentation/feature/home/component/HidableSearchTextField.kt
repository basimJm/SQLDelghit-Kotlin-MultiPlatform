package com.blackhand.sqldelight.android.presentation.feature.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HidableSearchTextField(
    isSearchActive: Boolean,
    onTextChanged: (String) -> Unit,
    text: String,
    openSearch: () -> Unit,
    closeSearch: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color.White)
    ) {
        AnimatedVisibility(visible = isSearchActive, enter = fadeIn(), exit = fadeOut()) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                shape = RoundedCornerShape(14),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(end = 20.dp)
                    .align(Alignment.Center)
            )
        }
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close Search", tint = Color.Black,
                modifier = Modifier.clickable { closeSearch() }
            )
        }
        AnimatedVisibility(
            visible = !isSearchActive,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "open Search", tint = Color.Black,
                modifier = Modifier
                    .clickable { openSearch() }
            )
        }

    }
}