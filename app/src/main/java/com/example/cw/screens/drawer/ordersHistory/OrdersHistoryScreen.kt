package com.example.cw.screens.drawer.ordersHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cw.R

@Composable
fun OrdersHistoryScreen(viewModel: OrdersHistoryViewModel){
    Column {
        Text(text = stringResource(id = R.string.ordersHistory))
    }
}