package com.example.cw.screens.base.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.screens.base.cart.widgets.CartPlantComponent

@Composable
fun CartScreen(viewModel: CartViewModel) {
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
                text = stringResource(id = R.string.total) + ": " + sum.value.toString() + "$",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
            )

        }

        LazyColumn {
            items(cartContent.value) { plant ->
                CartPlantComponent(plant = plant)
            }
        }
    }
}