package com.example.cw.screens.base.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.di.AppContainer
import com.example.cw.screens.base.favorite.FavoriteViewModel

class HomeFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build() {
        HomeScreen(
            HomeViewModel(
                plantsRepository = AppContainer.plantsRepository(),
                userService = AppContainer.userService,
                navHostController = navHostController,
                favoriteViewModel = FavoriteViewModel(
                    userService = AppContainer.userService,
                    plantsRepository = AppContainer.plantsRepository()
                )
            )
        )
    }
}