package com.example.cw.screens.plantDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.data.networking.NetworkingClient
import com.example.cw.data.plants.PlantsRepository
import com.example.cw.screens.plantDetails.widgets.DetailsContainer
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.pink
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PlantDetailsScreen(viewModel: PlantDetailsViewModel) {
    val plant = viewModel.plant.collectAsState()
    val quantity = viewModel.quantity.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .background(pink)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(pink)
        ) {
            if (loadingState.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            errorState.value?.let {
                Text(text = "Error: $it")
            }


            if (plant.value != null) {
                NetworkImage(url = plant.value!!.image)
            }

        }
        if (plant.value != null) {
            DetailsContainer(
                plant.value!!,
                quantity = quantity.value,
                onQuantityPlusTapped = { viewModel.onQuantityPlusTapped() },
                onQuantityMinusTapped = { viewModel.onQuantityMinusTapped() })
        }
    }

}
