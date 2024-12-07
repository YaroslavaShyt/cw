package com.example.cw.screens.plantDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlantDetailsScreen(viewModel: PlantDetailsViewModel) {
    Column {
        Text(
            text = "plant details " + viewModel
                .plant.value?.id
        )
    }
}