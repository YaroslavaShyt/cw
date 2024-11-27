package com.example.cw.screens.shippingAddresses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.screens.shippingAddresses.widgets.AddressRow

@Composable
fun ShippingAddressesScreen(viewModel: ShippingAddressesViewModel) {

    val addressesState = viewModel.addresses.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    val isAddingAddress = remember { mutableStateOf(false) }

    val country = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(20.dp)) {
        Row {
            Text(
                text = "My shipping addresses",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 26.sp),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            IconButton(onClick = {
                isAddingAddress.value = true  // Відкриваємо діалог
            }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }

        if (loadingState.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        errorState.value?.let {
            Text(text = "Error: $it")
        }

        LazyColumn {
            items(addressesState.value) { address ->
                AddressRow(isSelected = true, address = address)
            }
        }

        // Відображення діалогу для додавання нової адреси
        if (isAddingAddress.value) {
            AddAddressDialog(
                country = country.value,
                city = city.value,
                street = street.value,
                onCountryChange = { country.value = it },
                onCityChange = { city.value = it },
                onStreetChange = { street.value = it },
                onSave = {
                    viewModel.onAddAddressPressed(country.value, city.value, street.value)
                    isAddingAddress.value = false // Закриваємо діалог
                    country.value = ""
                    city.value = ""
                    street.value = ""
                },
                onCancel = {
                    isAddingAddress.value = false // Закриваємо діалог
                    country.value = ""
                    city.value = ""
                    street.value = ""
                }
            )
        }
    }
}


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
        onDismissRequest = onCancel,
        title = { Text(text = "Add New Address") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = country,
                    onValueChange = onCountryChange,
                    label = { Text("Country") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                TextField(
                    value = city,
                    onValueChange = onCityChange,
                    label = { Text("City") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                TextField(
                    value = street,
                    onValueChange = onStreetChange,
                    label = { Text("Street") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onSave) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}