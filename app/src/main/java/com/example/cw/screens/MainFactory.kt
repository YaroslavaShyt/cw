package com.example.cw.screens

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer

class MainFactory(private val navHostController: NavHostController) {

    @RequiresApi(android.os.Build.VERSION_CODES.O)
    @Composable
    fun Build() {
        MainScreen(
            navHostController = navHostController,
            viewModel = MainViewModel(
                _authService = AppContainer.authService,
                _userService = AppContainer.userService
            )
        )
    }
}