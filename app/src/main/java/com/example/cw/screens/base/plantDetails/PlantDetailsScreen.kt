package com.example.cw.screens.base.plantDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.screens.base.plantDetails.widgets.DetailsContainer
import com.example.cw.screens.base.widgets.MainBackButton
import com.example.cw.ui.theme.pink

@Composable
fun PlantDetailsScreen(viewModel: PlantDetailsViewModel) {
    val plant = viewModel.plant.collectAsState()
    val quantity = viewModel.quantity.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    var containerHeight by remember { mutableStateOf(300.dp) }
    val maxHeight = 500.dp

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


    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize()
    ) {
        if (plant.value != null) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            containerHeight =
                                (containerHeight - dragAmount.y.dp).coerceIn(
                                    300.dp,
                                    maxHeight
                                )
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .height(containerHeight) // Тут використовуємо змінну висоти
                ) {
                    DetailsContainer(
                        plant.value!!,
                        quantity = quantity.value,
                        onQuantityPlusTapped = { viewModel.onQuantityPlusTapped() },
                        onQuantityMinusTapped = { viewModel.onQuantityMinusTapped() }
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.padding(20.dp)) {
        MainBackButton {
            viewModel.onBackButtonTapped()
        }
    }
}

