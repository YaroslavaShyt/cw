package com.example.cw.screens

import androidx.compose.runtime.Composable

class HomeFactory {

    @Composable
    fun build() {
        HomeScreen(
            viewModel = HomeViewModel()
        )
    }
}