package com.example.cw.screens.base.plantDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer
import com.example.cw.screens.auth.AuthViewModel
import com.example.cw.screens.base.BaseViewModel


class PlantDetailsFactory(
    private val onBackTapped: () -> Unit,
    private val navHostController: NavHostController,
    private val onAuth: () -> Unit
) {

    @Composable
    fun Build(plantId: String) {
        PlantDetailsScreen(
            onBackTapped = onBackTapped,
            viewModel = PlantDetailsViewModel(
                plantId = plantId,
                plantRepository = AppContainer.plantsRepository(),
                userService = AppContainer.userService,
                navHostController = navHostController,
                onAuth = onAuth,
                baseViewModel = BaseViewModel(
                    userService = AppContainer.userService,
                    authService = AppContainer.authService,
                    navController = navHostController,
                    onAuth = onAuth,
                    authViewModel = AuthViewModel { onAuth() }
                )
            )
        )
    }
}