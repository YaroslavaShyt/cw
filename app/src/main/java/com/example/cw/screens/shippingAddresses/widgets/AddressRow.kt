package com.example.cw.screens.shippingAddresses.widgets

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.user.Address

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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButton(selected = isSelected, onClick = { onAddressSelected(address) })
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
        if (isSelected) {
            IconButton(onClick = { onEditAddress(address) }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
            IconButton(onClick = { onDeleteAddress(address) }) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
            }
        }

    }
}



