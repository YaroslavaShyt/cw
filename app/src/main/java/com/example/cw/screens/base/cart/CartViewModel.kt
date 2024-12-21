package com.example.cw.screens.base.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(plantsRepository: IPlantsRepository, userService: IUserService) : ViewModel(){

    private val _plantsRepository: IPlantsRepository = plantsRepository
    private val _userService: IUserService = userService

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _plants = MutableStateFlow<Map<Plant, Int>>(emptyMap())
    val plants: StateFlow<Map<Plant, Int>> = _plants

    private var _sum = MutableStateFlow(0.0)
    val sum: StateFlow<Double> = _sum

    init {
        getCartContent()
    }

    fun onQuantityPlusTapped(id: String) {
        var newQuantity = 1
        _plants.value = _plants.value.toMutableMap().apply {
            this.entries.firstOrNull { it.key.id == id }?.let {
                if (it.value > 0) {
                    newQuantity = it.value + 1

                    this[it.key] = newQuantity
                }

            }
        }
        viewModelScope.launch {
            _userService.updateUserCart(mapOf(id to newQuantity))
            recalculateSum()

        }
    }

    fun onDeleteButtonTapped(id: String){
        println("tapped")
        viewModelScope.launch {
            _userService.removeFromCart(id)
        }
        val map = _plants.value.toMutableMap().filter { plant -> plant.key.id != id }
        _plants.value = map
    }

    fun onQuantityMinusTapped(id: String) {
        var newQuantity = 1
        _plants.value = _plants.value.toMutableMap().apply {
            this.entries.firstOrNull { it.key.id == id }?.let {
                if (it.value > 1) {

                    newQuantity = it.value - 1

                    this[it.key] = newQuantity
                }

            }
        }
        if (newQuantity >= 0) {
            viewModelScope.launch {
                _userService.updateUserCart(mapOf(id to newQuantity))
                recalculateSum()

            }
        }
    }

    private fun recalculateSum() {
        _sum.value = _plants.value.entries.sumByDouble { (plant, quantity) ->
            plant.price.toDouble() * quantity
        }
    }

    private fun getCartContent() {
        _loading.value = true
        _error.value = null
        if (_userService.user.value != null) {
            viewModelScope.launch {
                try {
                    val ids = _userService.user.value!!.cart.keys.toList()
                    val plantsList = _plantsRepository.getPlantsById(ids = ids)

                    _plants.value = plantsList.zip(_userService.user.value!!.cart.values).toMap()

                    recalculateSum()
                } catch (e: Exception) {
                    _error.value = e.localizedMessage
                } finally {
                    _loading.value = false
                }
            }
        }
    }
}
