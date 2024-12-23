package com.example.cw.screens.base.drawer.ordersHistory

import androidx.compose.runtime.Composable
import com.example.cw.domain.di.AppContainer

class OrdersHistoryFactory {

    @Composable
    fun Build() {
        OrdersHistoryScreen(
            viewModel = OrdersHistoryViewModel(
                userService = AppContainer.userService,
                plantsRepository = AppContainer.plantsRepository()
            )
        )
    }
}