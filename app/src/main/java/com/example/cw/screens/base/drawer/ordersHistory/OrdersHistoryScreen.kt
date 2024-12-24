package com.example.cw.screens.base.drawer.ordersHistory

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.screens.base.cart.confirmOrder.widgets.PurchaseListComponent
import com.example.cw.screens.base.widgets.NothingFoundPlaceholder
import com.example.cw.ui.theme.olive

@Composable
fun OrdersHistoryScreen(viewModel: OrdersHistoryViewModel) {
    val orders = viewModel.orders.collectAsState()
    val plants = viewModel.orderedPlants.collectAsState()
    val loading = viewModel.loading.collectAsState()
    val error = viewModel.error.collectAsState()

    Column(
        Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.ordersHistory),
            style = MaterialTheme.typography.bodyLarge
        )

        if (loading.value) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = olive,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp)
                )
            }
        }

        error.value?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (orders.value.isEmpty()) {
            NothingFoundPlaceholder()
        }

        if (!loading.value && orders.value.isNotEmpty()) {
            orders.value.map { order ->
                Column(
                    Modifier
                        .padding(vertical = 8.dp)
                        .height(200.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = order.date,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Thin
                            ),
                        )
                        Text(
                            text = order.code.substring(0, 7),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Thin
                            ),
                        )
                        Text(
                            text = order.status,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Thin
                            ),
                        )
                    }

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {
                        order.plants.map { plantId ->
                            val plant = plants.value.find { it.id == plantId }
                            if (plant != null) {
                                PurchaseListComponent(plant = plant)
                            }
                        }
                    }
                }
            }
        }
    }
}
