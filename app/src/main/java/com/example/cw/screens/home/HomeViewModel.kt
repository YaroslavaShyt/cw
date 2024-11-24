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
import java.util.Locale


class HomeViewModel : ViewModel(), KoinComponent {
    private val plantsRepository: IPlantsRepository by inject()

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants

    private val _plantsFiltered = MutableStateFlow<List<Plant>>(emptyList())
    val plantsFiltered: StateFlow<List<Plant>> = _plantsFiltered

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _selectedCategory = MutableStateFlow<String>("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    init {
        fetchPlants()
    }

    private fun fetchPlants() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val plantList = plantsRepository.getAllPlants()
                val categories = plantsRepository.getPlantsCategories()
                _plants.value = plantList
                _plantsFiltered.value = _plants.value
                _categories.value = categories
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    fun onCategorySelected(newCategory: String) {
        _selectedCategory.value = newCategory
        if (newCategory != "All") {
            _plantsFiltered.value = _plants.value.filter { plant ->
                plant.category.lowercase(Locale.ROOT) == newCategory.lowercase(Locale.ROOT)


            }
        } else {
            _plantsFiltered.value = _plants.value
        }

    }

    fun onSearchInputted(request: String) {
        if (request.isEmpty()) {
            _plantsFiltered.value = _plants.value
        } else {
            _plantsFiltered.value =
                _plants.value.filter { plant ->
                    plant.name.lowercase(Locale.ROOT)
                        .contains(request.lowercase()) && _selectedCategory.value.lowercase() == plant.category.lowercase()
                }
        }

    }
}
