package com.example.cw.screens.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer
import com.example.cw.screens.MainViewModel
import com.example.cw.screens.auth.AuthViewModel

class BaseFactory(
    private val navHostController: NavHostController,
    private val onAuth: () -> Unit
) {

    @Composable
    fun Build() {
        BaseScreen(
            viewModel = BaseViewModel(
                userService = AppContainer.userService,
                authService = AppContainer.authService,
                navController = navHostController,
                authViewModel = AuthViewModel { },
                onAuth = onAuth
            ),
            navHostController = navHostController,
            mainViewModel = MainViewModel(
                AppContainer.authService,
            ),
        )
    }
}