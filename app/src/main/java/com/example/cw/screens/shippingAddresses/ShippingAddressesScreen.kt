package com.example.cw.screens.shippingAddresses

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cw.screens.shippingAddresses.widgets.AddressRow

@Composable
fun ShippingAddressesScreen(viewModel: ShippingAddressesViewModel){
    Column {
        Text(text = "My shipping addresses")
        AddressRow(isSelected = true)
    }
}

