package com.example.cw.screens.base.plantDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer


class PlantDetailsFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build(plantId: String) {
        PlantDetailsScreen(
            viewModel = PlantDetailsViewModel(
                plantId = plantId,
                plantRepository = AppContainer.plantsRepository(),
                userService = AppContainer.userService,
                navHostController = navHostController,
            )
        )
    }
}