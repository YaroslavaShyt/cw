package com.example.cw.screens.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BaseFactory : KoinComponent {
    private val userService: IUserService by inject()

    @Composable
    fun Build(navHostController: NavHostController) {
        BaseScreen(
            viewModel = BaseViewModel(userService = userService),
            navController = navHostController
        )
    }
}