package com.example.cw.screens.base.favorite

import androidx.compose.runtime.Composable
import com.example.cw.domain.di.AppContainer


class FavoriteFactory {
    @Composable
    fun Build() {
        FavoriteScreen(
            viewModel = FavoriteViewModel(
                userService = AppContainer.userService,
                plantsRepository = AppContainer.plantsRepository()
            )
        )
    }
}