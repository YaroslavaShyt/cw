package com.example.cw.screens.home.main

import androidx.compose.runtime.Composable
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.home.favorite.FavoriteViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeFactory : KoinComponent{
    private val userService: IUserService by inject()
    private val plantsRepository: IPlantsRepository by inject()

    @Composable
    fun build(){
        HomeScreen(
            HomeViewModel(
            plantsRepository = plantsRepository,
            userService = userService,
            favoriteViewModel = FavoriteViewModel(
                userService = userService,
                plantsRepository = plantsRepository
            )
        )
        )
    }
}