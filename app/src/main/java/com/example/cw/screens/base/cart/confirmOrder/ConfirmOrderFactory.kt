package com.example.cw.screens.base.cart.confirmOrder

import androidx.compose.runtime.Composable
import com.example.cw.data.plants.Plant
import com.example.cw.domain.di.AppContainer

class ConfirmOrderFactory(
    private val plants: List<Plant>,
    private val totalSum: Double,
) {

    @Composable
    fun Build() {
        ConfirmOrderScreen(
            viewModel = ConfirmOrderViewModel(
                plants = plants,
                totalSum = totalSum,
                userService = AppContainer.userService
            )
        )
    }
}