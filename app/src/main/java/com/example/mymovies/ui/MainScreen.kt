package com.example.mymovies.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { MainAppBar() }
    ) { padding ->
        MediaList(modifier = Modifier.padding(padding))
    }
}