package com.example.cw.screens.base.drawer.shippingAddresses.widgets

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.user.Address
import com.example.cw.ui.theme.olive

@Composable
fun AddressRow(
    isSelected: Boolean,
    address: Address,
    onAddressSelected: (Address) -> Unit,
    onDeleteAddress: (Address) -> Unit,
    onEditAddress: (Address) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { _ ->
                        onAddressSelected(address)
                    }
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                colors = RadioButtonDefaults.colors().copy(
                    selectedColor = olive
                ),
                selected = isSelected, onClick = { onAddressSelected(address) })
            Column {
                Text(
                    text = address.country,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp)
                )
                Text(
                    text = address.city,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp)
                )
                Text(
                    text = address.street,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp)
                )

            }
        }

        if (isSelected) {
            IconButton(onClick = { onDeleteAddress(address) }) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
            }
        }

    }
}



