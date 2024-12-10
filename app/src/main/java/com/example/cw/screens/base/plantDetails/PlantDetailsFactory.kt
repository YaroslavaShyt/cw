package com.example.cw.screens.base.plantDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.plants.IPlantsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlantDetailsFactory() : KoinComponent {
    private val plantsRepository: IPlantsRepository by inject()

    @Composable
    fun build(plantId: String) {
        PlantDetailsScreen(viewModel = PlantDetailsViewModel(
            plantId = plantId,
            plantRepository = plantsRepository
        )
        )
    }
}