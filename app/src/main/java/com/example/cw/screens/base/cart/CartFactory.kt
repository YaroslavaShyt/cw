package com.example.cw.screens.base.cart

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.cw.domain.di.AppContainer
import com.example.cw.screens.base.favorite.FavoriteViewModel

class CartFactory {

    @RequiresApi(android.os.Build.VERSION_CODES.O)
    @Composable
    fun Build() {
        CartScreen(
            viewModel = CartViewModel(
                userService = AppContainer.userService,
                plantsRepository = AppContainer.plantsRepository(),
                favorites = FavoriteViewModel(
                    AppContainer.userService,
                    plantsRepository = AppContainer.plantsRepository()
                )
            )
        )
    }
}