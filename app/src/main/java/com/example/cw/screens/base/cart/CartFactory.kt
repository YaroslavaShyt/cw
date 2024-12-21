package com.example.cw.screens.base.cart

import androidx.compose.runtime.Composable
import com.example.cw.domain.di.AppContainer

class CartFactory {

    @Composable
    fun Build() {
        CartScreen(
            viewModel = CartViewModel(
                userService = AppContainer.userService,
                plantsRepository = AppContainer.plantsRepository()
            )
        )
    }
}