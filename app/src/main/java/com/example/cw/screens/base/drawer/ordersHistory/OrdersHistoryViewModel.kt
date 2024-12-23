package com.example.cw.screens.base.drawer.ordersHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.plants.Plant
import com.example.cw.data.user.Order
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrdersHistoryViewModel(userService: IUserService, plantsRepository: IPlantsRepository) :
    ViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    private val _orderedPlants = MutableStateFlow<List<Plant>>(emptyList())
    val orderedPlants: StateFlow<List<Plant>> = _orderedPlants

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        if (userService.user.value != null) {
            viewModelScope.launch {
                try {
                    _loading.value = true
                    if (userService.user.value!!.ordersHistory.isNotEmpty()) {
                        _orders.value = userService.user.value!!.ordersHistory
                        val ids = mutableListOf<String>()
                        val orders = userService.user.value!!.ordersHistory
                        for (order in orders) {
                            for (plantID in order.plants) {
                                if (!ids.contains(plantID)) {
                                    ids.add(plantID)
                                }
                            }
                        }
                        _orderedPlants.value = plantsRepository.getPlantsById(ids = ids)
                    }
                } catch (e: Exception) {
                    _error.value = "Error loading orders: ${e.message}"
                } finally {
                    _loading.value = false
                }
            }
        }
    }

}