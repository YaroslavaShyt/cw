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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.screens.shippingAddresses.widgets.AddressRow
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.neatGreen
import com.example.cw.ui.theme.olive

@Composable
fun ShippingAddressesScreen(viewModel: ShippingAddressesViewModel) {

    val addressesState = viewModel.addresses.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()
    val selectedAddressState = viewModel.selectedAddress.collectAsState()

    val isAddingAddress = remember { mutableStateOf(false) }
    val isEditingAddress = remember { mutableStateOf(false) }

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
                isAddingAddress.value = true
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
                AddressRow(
                    isSelected = address == selectedAddressState.value,
                    address = address,
                    onAddressSelected = { viewModel.onAddressSelected(address) },
                    onDeleteAddress = { viewModel.onDeleteAddressPressed(address) },
                    onEditAddress = {isEditingAddress.value = true}
                )
            }
        }

        if (isAddingAddress.value or isEditingAddress.value) {
            if(isEditingAddress.value){
                country.value = selectedAddressState.value.country
                city.value = selectedAddressState.value.city
                street.value = selectedAddressState.value.street
            }
            AddAddressDialog(
                country =  country.value,
                city = city.value,
                street = street.value,
                onCountryChange = { country.value = it },
                onCityChange = { city.value = it },
                onStreetChange = { street.value = it },
                onSave = {
                    if(isAddingAddress.value) {
                        viewModel.onAddAddressPressed(country.value, city.value, street.value)
                        isAddingAddress.value = false

                    }
                    if(isEditingAddress.value){
                        viewModel.onEditAddressPressed(country.value, city.value, street.value)
                        isEditingAddress.value = false
                    }
                    country.value = ""
                    city.value = ""
                    street.value = ""
                },
                onCancel = {
                    isEditingAddress.value = false
                    isAddingAddress.value = false
                    country.value = ""
                    city.value = ""
                    street.value = ""
                }
            )
        }
    }
}


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
        onDismissRequest = onCancel,
        title = { Text(text = "Add New Address") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = neatGreen,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = neatGreen,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                    ),
                    value = country,
                    onValueChange = onCountryChange,
                    label = {
                        Text(
                            "Country",
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = neatGreen,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = neatGreen,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                    ),
                    value = city,
                    onValueChange = onCityChange,
                    label = {
                        Text(
                            "City",
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = olive,
                        unfocusedIndicatorColor = neatGreen,
                        focusedLabelColor = olive,
                        unfocusedLabelColor = neatGreen,
                        focusedPlaceholderColor = mainWhite,
                        unfocusedPlaceholderColor = mainWhite,
                    ),
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
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onSave) {
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