package com.example.cw.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.services.IAuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainFactory : KoinComponent {
    private val authService: IAuthService by inject()

    @Composable
    fun Build(navHostController: NavHostController) {
        MainScreen(
            navHostController = navHostController,
            viewModel = MainViewModel(_authService = authService)
        )
    }
}