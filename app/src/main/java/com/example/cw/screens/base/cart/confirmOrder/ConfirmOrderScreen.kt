package com.example.cw.screens.base.cart.confirmOrder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cw.data.user.Address
import com.example.cw.data.user.ShippingType
import com.example.cw.screens.base.cart.confirmOrder.widgets.AddressContainer
import com.example.cw.screens.base.cart.confirmOrder.widgets.ConfirmOrderButton
import com.example.cw.screens.base.cart.confirmOrder.widgets.OrderOnTheWayPlaceholder
import com.example.cw.screens.base.cart.confirmOrder.widgets.PaymentForm
import com.example.cw.screens.base.cart.confirmOrder.widgets.PurchaseListComponent
import com.example.cw.screens.base.cart.confirmOrder.widgets.ShippingContainer
import com.example.cw.ui.theme.olive

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConfirmOrderScreen(viewModel: ConfirmOrderViewModel) {
    val selectedAddress = viewModel.selectedAddress.collectAsState()
    val selectedShipping = viewModel.selectedShipping.collectAsState()
    val cardNumber = viewModel.cardNumber.collectAsState()
    val cvc = viewModel.cvc.collectAsState()
    val expirationDate = viewModel.expireDate.collectAsState()
    val isButtonActive = viewModel.isButtonActive.collectAsState()
    val isOrderSuccess = viewModel.isSuccess.collectAsState()
    if (isOrderSuccess.value) {
        OrderOnTheWayPlaceholder(
            onTextButtonTapped = { viewModel.onToPurchasesTapped() }
        )
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Confirm order",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 10.dp),
            ) {
                Text(
                    text = "Articles",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(8.dp),

                    )
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    viewModel.order.map { plant -> PurchaseListComponent(plant = plant) }
                }

                Text(
                    text = "Payment Info",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(8.dp),
                )
                PaymentForm(
                    cardNumber = cardNumber.value,
                    onCardNumberInputted = { viewModel.onCardNumberChanged(it) },
                    cvc = cvc.value,
                    onCVVInputted = { viewModel.onCVCChanged(it) },
                    expirationDate = expirationDate.value,
                    onExpirationDateInputted = { viewModel.onExpireDateChanged(it) }
                )
                Text(
                    text = "Shipping address",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(8.dp),
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    viewModel.addresses.map { address: Address ->
                        AddressContainer(
                            isSelected = address == selectedAddress.value,
                            address = address,
                            select = { viewModel.onAddressSelected(address) }
                        )
                    }
                }
                Text(
                    text = "Shipping type",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(8.dp),
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    viewModel.shippingTypes.map { shippingType: ShippingType ->
                        ShippingContainer(
                            shippingType,
                            isSelected = viewModel.selectedShipping.value == shippingType
                        ) { viewModel.onShippingSelected(shippingType) }
                    }
                }


                Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)) {
                    Text(
                        text = "Total: ",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                    )
                    Text(
                        text = (viewModel.totalToPay + if (selectedShipping.value == ShippingType.Fast) 20 else 0).toString() + "$",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700,
                            color = olive
                        ),
                    )
                }
            }

            ConfirmOrderButton(isActive = isButtonActive.value) {
                viewModel.onConfirmButtonPressed()
            }
        }
    }
}