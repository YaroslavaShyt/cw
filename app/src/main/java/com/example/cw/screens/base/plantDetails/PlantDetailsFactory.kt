package com.example.cw.screens.base.plantDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlantDetailsFactory(navHostController: NavHostController) : KoinComponent {
    private val plantsRepository: IPlantsRepository by inject()
    private val _navHostController: NavHostController = navHostController
    private val userService: IUserService by inject()


    @Composable
    fun build(plantId: String) {
        PlantDetailsScreen(
            viewModel = PlantDetailsViewModel(
                plantId = plantId,
                plantRepository = plantsRepository,
                userService = userService,
                navHostController = _navHostController
            )
        )
    }
}