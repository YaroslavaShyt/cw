package com.example.cw.screens.base.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CartViewModel(plantsRepository: IPlantsRepository, userService: IUserService) : ViewModel(),
    KoinComponent {

    private val _plantsRepository: IPlantsRepository = plantsRepository
    private val _userService: IUserService = userService

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants

    private var _sum = MutableStateFlow(0.0)
    val sum: StateFlow<Double> = _sum

    init {
        getCartContent()
    }

    private fun getCartContent() {
        _loading.value = true
        _error.value = null
        if (_userService.user.value != null) {
            viewModelScope.launch {
                try {
                    val ids = _userService.user.value!!.cart.keys.toList()
                    val plantsList =
                        _plantsRepository.getPlantsById(ids = ids)
                    _plants.value = plantsList

                    for (plant in plantsList) {
                        var quantity = 1
                        for (pair in _userService.user.value!!.cart) {
                            if (pair.key == plant.id) {
                                quantity = pair.value
                            }
                        }
                        _sum.value += plant.price.toDouble() * quantity
                    }
                } catch (e: Exception) {
                    _error.value = e.localizedMessage
                } finally {
                    _loading.value = false
                }
            }
        }

    }
}