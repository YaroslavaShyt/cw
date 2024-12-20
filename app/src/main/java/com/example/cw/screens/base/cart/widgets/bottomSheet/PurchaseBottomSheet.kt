package com.example.cw.screens.base.cart.widgets.bottomSheet

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cw.data.plants.Plant

@Composable
fun PurchaseBottomSheet(order: List<Plant>) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Confirm order",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Text(
                text = "Articles",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                order.map { plant -> PurchaseListComponent(plant = plant) }
            }

            Text(
                text = "Payment Info",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )
            PaymentForm()

            Text(
                text = "Shipping address",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )

            Text(
                text = "Shipping type",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )
        }


    }
}