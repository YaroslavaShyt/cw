package com.example.cw.screens.base.drawer.shippingAddresses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.screens.base.drawer.shippingAddresses.widgets.AddAddressDialog
import com.example.cw.screens.base.drawer.shippingAddresses.widgets.AddressRow
import com.example.cw.ui.theme.neatGreen

@Composable
fun ShippingAddressesScreen(viewModel: ShippingAddressesViewModel) {
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()
    val addressesState = viewModel.addresses.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()
    val selectedAddressState = viewModel.selectedAddress.collectAsState()

    val isAddingAddress = remember { mutableStateOf(false) }
    val isEditingAddress = remember { mutableStateOf(false) }

    val country = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }

    val scaffoldState = remember { androidx.compose.material3.SnackbarHostState() }

    Column(modifier = Modifier.padding(20.dp)) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 15.dp)
        ) {
            Text(
                text = "My shipping addresses",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 26.sp),
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
                    onEditAddress = { isEditingAddress.value = true }
                )
            }
        }

        if (isAddingAddress.value or isEditingAddress.value) {
            if (isEditingAddress.value) {
                country.value = selectedAddressState.value.country
                city.value = selectedAddressState.value.city
                street.value = selectedAddressState.value.street
            }
            AddAddressDialog(
                country = country.value,
                city = city.value,
                street = street.value,
                onCountryChange = { country.value = it },
                onCityChange = { city.value = it },
                onStreetChange = { street.value = it },
                onSave = {
                    viewModel.onAddAddressPressed(country.value, city.value, street.value)
                    isAddingAddress.value = false

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

        snackbarMessage?.let { message ->
            androidx.compose.material3.SnackbarHost(
                hostState = scaffoldState,
            ) {
                androidx.compose.material3.Snackbar(
                    containerColor = neatGreen,
                    action = null,
                    actionOnNewLine = false
                ) {
                    Text(text = message, style = MaterialTheme.typography.bodyMedium)
                }
            }

            LaunchedEffect(message) {
                scaffoldState.showSnackbar(message)
                viewModel.clearSnackbarMessage()
            }
        }
    }
}
