package com.example.cw.screens.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer
import com.example.cw.screens.MainViewModel

class BaseFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build() {
        BaseScreen(
            viewModel = BaseViewModel(
                userService = AppContainer.userService,
                authService = AppContainer.authService,
                navController = navHostController,
              //  context = context
            ),
            navHostController = navHostController,
            mainViewModel = MainViewModel(
                AppContainer.authService,
            ),
        )
    }
}