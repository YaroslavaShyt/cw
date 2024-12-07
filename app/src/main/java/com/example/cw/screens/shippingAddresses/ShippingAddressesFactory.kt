package com.example.cw.screens.shippingAddresses

import androidx.compose.runtime.Composable
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShippingAddressesFactory : KoinComponent {
    private val userService: IUserService by inject()

    @Composable
    fun build() {
        ShippingAddressesScreen(
            viewModel = ShippingAddressesViewModel(
                userService = userService
            )
        )
    }
}