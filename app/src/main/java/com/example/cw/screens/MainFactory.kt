package com.example.cw.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer

class MainFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build() {
        MainScreen(
            navHostController = navHostController,
            viewModel = MainViewModel(
                _authService = AppContainer.authService,

            )
        )
    }
}