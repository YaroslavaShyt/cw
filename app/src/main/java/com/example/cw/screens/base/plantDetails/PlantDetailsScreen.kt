package com.example.cw.screens.base.plantDetails


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.screens.base.plantDetails.widgets.AddToCartButton
import com.example.cw.screens.base.plantDetails.widgets.DetailsContainer
import com.example.cw.screens.base.widgets.AuthDialog
import com.example.cw.screens.base.widgets.MainBackButton
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.pink

@Composable
fun PlantDetailsScreen(viewModel: PlantDetailsViewModel) {
    val plant = viewModel.plant.collectAsState()
    val quantity = viewModel.quantity.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()
    val cartState = viewModel.isInCart.collectAsState()
    var containerHeight by remember { mutableStateOf(400.dp) }
    val maxHeight = 500.dp
    val minHeight = 400.dp

    val isAuthPopupShown = remember { mutableStateOf(false) }
    val isAuthorized = viewModel.isAuthorized

    if (isAuthPopupShown.value) {
        AuthDialog(
            onAuthorize = { viewModel.onAuthButtonPressed() },
            onDismiss = { isAuthPopupShown.value = false }
        )
    }
    Column(
        modifier = Modifier
            .background(pink)
            .fillMaxWidth()
            .height(500.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(pink)
                .height(500.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loadingState.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            errorState.value?.let {
                Text(text = "${stringResource(id = R.string.error)}: $it")
            }

            if (plant.value != null) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(500.dp), contentAlignment = Alignment.Center
                ) {
                    NetworkImage(url = plant.value!!.image, modifier = Modifier.height(400.dp))
                }
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
                                    minHeight,
                                    maxHeight
                                )
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .height(containerHeight)
                ) {
                    DetailsContainer(
                        plant.value!!,
                        isAddedToCart = cartState.value,
                        quantity = quantity.value,
                        onQuantityPlusTapped = { viewModel.onQuantityPlusTapped() },
                        onQuantityMinusTapped = { viewModel.onQuantityMinusTapped() }

                    )
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            mainWhite,
                        ),
                    )
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
            ) {
                AddToCartButton(
                    isAddedToCart = cartState.value
                ) {
                    if (cartState.value) {
                        viewModel.onToCartButtonPressed()
                    } else {
                        if (isAuthorized) {
                            viewModel
                                .onAddToCartButtonPressed()
                        } else {
                            isAuthPopupShown.value = true
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.padding(start = 20.dp)) {
        MainBackButton {
            viewModel.onBackButtonTapped()
        }
    }

}

