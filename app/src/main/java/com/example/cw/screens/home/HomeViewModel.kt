package com.example.cw.screens.home

import androidx.lifecycle.ViewModel
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), KoinComponent {
    private val plantsRepository: IPlantsRepository by inject()

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchPlants()
    }

    private fun fetchPlants() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val plantList = plantsRepository.getAllPlants()
                _plants.value = plantList
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}
