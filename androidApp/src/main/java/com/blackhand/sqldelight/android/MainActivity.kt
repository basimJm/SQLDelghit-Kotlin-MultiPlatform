package com.blackhand.sqldelight.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.blackhand.sqldelight.android.presentation.feature.home.component.NoteScreen
import com.blackhand.sqldelight.android.presentation.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewmodel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NoteScreen(viewmodel)

            }
        }
    }
}

