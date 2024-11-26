package com.example.cw.screens.shippingAddresses.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AddressRow(isSelected: Boolean, ){
    Row {
        RadioButton(selected = isSelected, onClick = { /*TODO*/ })
        Column {
            Text(text = "Ukraine")
            Text(text = "Ukraine")
            Text(text = "Ukraine")

        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
        }
    }
}