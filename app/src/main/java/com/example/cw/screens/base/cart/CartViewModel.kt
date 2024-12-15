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

    init {
        getCartContent()
    }

    private fun getCartContent() {
        _loading.value = true
        _error.value = null
        if (_userService.user.value != null) {
            viewModelScope.launch {
                try {
                    val plantsList = _plantsRepository.getPlantsById(_userService.user.value!!.cart)
                    _plants.value = plantsList
                } catch (e: Exception) {
                    _error.value = e.localizedMessage
                } finally {
                    _loading.value = false
                }
            }
        }

    }
}