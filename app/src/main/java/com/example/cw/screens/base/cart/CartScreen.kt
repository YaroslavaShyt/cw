package com.example.cw.screens.base.cart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.screens.base.cart.confirmOrder.ConfirmOrderFactory
import com.example.cw.screens.base.cart.widgets.CartPlantComponent
import com.example.cw.screens.base.cart.widgets.MakePurchaseButton
import com.example.cw.ui.theme.mainWhite

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: CartViewModel) {
    val cartContent = viewModel.plants.collectAsState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val bottomSheet = rememberModalBottomSheetState(true)
    var bottomSheetFraction by remember {
        mutableStateOf(0.9f)
    }
    BaseContent(viewModel = viewModel)
    PurchaseButton(viewModel = viewModel, onPressed = { isBottomSheetOpen = !isBottomSheetOpen })

    if (isBottomSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheet,
            modifier = Modifier.fillMaxHeight(bottomSheetFraction),
            containerColor = mainWhite,
            onDismissRequest = { isBottomSheetOpen = !isBottomSheetOpen }) {
            ConfirmOrderFactory(
                onSuccess = { bottomSheetFraction = 0.7f },
                onToPurchases = { isBottomSheetOpen = !isBottomSheetOpen },
                plants = cartContent.value.keys.toList(),
                totalSum = viewModel.sum.value ?: 0.0,
                isOrderSuccess = bottomSheetFraction == 0.7f
            ).Build()
        }
    }


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