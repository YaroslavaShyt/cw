package com.example.cw.screens.base.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import com.example.cw.R
import com.example.cw.data.plants.Plant
import com.example.cw.screens.base.cart.widgets.CartPlantComponent
import com.example.cw.screens.base.cart.widgets.MakePurchaseButton
import kotlinx.coroutines.launch

@Composable
fun CartScreen(viewModel: CartViewModel) {
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    val openBottomSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.show()
        }
    }

    BottomSheetScaffold(

        sheetContent = {
            // This is where you define the content inside the bottom sheet
            Box(modifier = Modifier.padding(16.dp)) {
                Text("This is your Bottom Sheet content")
            }
        },
        sheetElevation = 16.dp,
        content = {
            BaseContent(viewModel = viewModel)
            PurchaseButton(viewModel, onPressed = openBottomSheet)
        })

}

@Composable
private fun BaseContent(viewModel: CartViewModel) {
    val cartContent = viewModel.plants.collectAsState()
    val sum = viewModel.sum.collectAsState()

    Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.cart),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Text(
                text = stringResource(id = R.string.total) + ": " + String.format(
                    "%.2f",
                    sum.value
                ) + "$",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
            )

        }

        LazyColumn {
            items(cartContent.value.toList()) { plant ->
                CartPlantComponent(
                    plant = plant.first,
                    quantity = plant.second,
                    onQuantityPlusTapped = {
                        viewModel.onQuantityPlusTapped(
                            plant.first.id
                        )
                    },
                    onQuantityMinusTapped = {
                        viewModel.onQuantityMinusTapped(
                            plant.first.id
                        )
                    },
                    onDeleteButtonTapped = { viewModel.onDeleteButtonTapped(plant.first.id) }
                )
            }
        }
    }
}

@Composable
private fun PurchaseButton(viewModel: CartViewModel, onPressed: () -> Unit) {
    val cartContent = viewModel.plants.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (cartContent.value.isNotEmpty()) {
            MakePurchaseButton {
                onPressed()
            }
        }

    }
}