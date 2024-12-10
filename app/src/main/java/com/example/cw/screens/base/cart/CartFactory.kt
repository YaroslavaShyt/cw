package com.example.cw.screens.base.cart

import androidx.compose.runtime.Composable
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CartFactory : KoinComponent {
    private val userService: IUserService by inject()
    private val plantsRepository: IPlantsRepository by inject()

    @Composable
    fun build() {
        CartScreen(
            viewModel = CartViewModel(
                userService = userService,
                plantsRepository = plantsRepository
            )
        )
    }
}