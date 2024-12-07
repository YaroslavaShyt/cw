package com.example.cw.screens.plantDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantDetailsViewModel(
    plantId: String,
    plantRepository: IPlantsRepository,
) : ViewModel() {
    private val _plant = MutableStateFlow<Plant?>(null)
    val plant: StateFlow<Plant?> = _plant

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading


    init {
        if (plantId.isNotEmpty()) {
            _loading.value = true
            _error.value = null
            viewModelScope.launch {
                try {
                    _plant.value = plantRepository.getPlantsById(listOf(plantId)).first()
                } catch (e: Exception) {
                    _error.value = e.localizedMessage
                } finally {
                    _loading.value = false
                }
            }
        }
    }
}