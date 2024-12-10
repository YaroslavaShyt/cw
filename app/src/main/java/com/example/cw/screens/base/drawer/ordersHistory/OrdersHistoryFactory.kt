package com.example.cw.screens.base.drawer.ordersHistory

import androidx.compose.runtime.Composable
import com.example.cw.domain.services.IUserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OrdersHistoryFactory : KoinComponent{
    private val userService: IUserService by inject()


    @Composable
    fun build(){
        OrdersHistoryScreen(
            viewModel = OrdersHistoryViewModel(
                userService = userService
            )
        )
    }
}