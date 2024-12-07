package com.example.cw.screens.home.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.home.favorite.FavoriteViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeFactory(navHostController: NavHostController) : KoinComponent {
    private val userService: IUserService by inject()
    private val plantsRepository: IPlantsRepository by inject()
    private val _navHostController: NavHostController = navHostController

    @Composable
    fun build() {
        HomeScreen(
            HomeViewModel(
                plantsRepository = plantsRepository,
                userService = userService,
                navHostController = _navHostController,
                favoriteViewModel = FavoriteViewModel(
                    userService = userService,
                    plantsRepository = plantsRepository
                )
            )
        )
    }
}