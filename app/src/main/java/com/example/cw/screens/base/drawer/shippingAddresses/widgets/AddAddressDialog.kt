package com.example.cw.screens.base.drawer.shippingAddresses.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.olive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressDialog(
    country: String,
    city: String,
    street: String,
    onCountryChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onStreetChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        containerColor = mainWhite,
        onDismissRequest = onCancel,
        title = {
            Text(
                text = "Add New Address",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = olive,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = olive,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                        containerColor = mainWhite,
                    ),
                    value = country,
                    onValueChange = onCountryChange,
                    label = {
                        Text(
                            "Country",
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    textStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = olive,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = olive,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                        containerColor = mainWhite,

                        ),
                    value = city,
                    onValueChange = onCityChange,
                    label = {
                        Text(
                            "City",
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    textStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = olive,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = olive,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                        containerColor = mainWhite,
                    ),
                    textStyle = MaterialTheme.typography.titleMedium,
                    value = street,
                    onValueChange = onStreetChange,
                    label = {
                        Text(
                            "Street",
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave()
            }) {
                Text("Save", style = MaterialTheme.typography.titleSmall.copy(color = olive))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel", style = MaterialTheme.typography.titleSmall.copy(color = olive))
            }
        }
    )
}