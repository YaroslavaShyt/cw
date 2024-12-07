package com.example.cw.screens.ordersHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.plants.Plant
import com.example.cw.data.user.Order
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class OrdersHistoryViewModel(userService: IUserService): ViewModel(), KoinComponent{
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            if (userService.user.ordersHistory.isNotEmpty()) {
                _orders.value = userService.user.ordersHistory
            }
        }
    }
}