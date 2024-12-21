package com.example.cw.screens.base.drawer.shippingAddresses

import androidx.compose.runtime.Composable
import com.example.cw.domain.di.AppContainer


class ShippingAddressesFactory {

    @Composable
    fun Build() {
        ShippingAddressesScreen(
            viewModel = ShippingAddressesViewModel(
                userService = AppContainer.userService
            )
        )
    }
}