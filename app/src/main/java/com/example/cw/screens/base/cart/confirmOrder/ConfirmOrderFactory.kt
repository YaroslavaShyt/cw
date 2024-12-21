package com.example.cw.screens.base.cart.confirmOrder

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.cw.data.plants.Plant
import com.example.cw.domain.di.AppContainer

class ConfirmOrderFactory(
    private val plants: List<Plant>,
    private val totalSum: Double,
    private val onSuccess: ()->Unit,
    private val onToPurchases: ()->Unit,
    private val isOrderSuccess: Boolean,
) {

    @RequiresApi(android.os.Build.VERSION_CODES.O)
    @Composable
    fun Build() {
        ConfirmOrderScreen(
            viewModel = ConfirmOrderViewModel(
                plants = plants,
                totalSum = totalSum,
                userService = AppContainer.userService,
                onSuccess,
                onToPurchases,
                isOrderSuccess
            )
        )
    }
}