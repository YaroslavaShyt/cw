package com.example.cw.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.cw.data.handlers.LocalizationHandler
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainFactory : KoinComponent {
    private val authService: IAuthService by inject()

    @Composable
    fun Build(navHostController: NavHostController) {

        MainScreen(
            navHostController = navHostController,
            viewModel = MainViewModel(
                _authService = authService,
            )
        )
    }
}