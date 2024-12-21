package com.example.cw.screens.base.home

import androidx.lifecycle.ViewModel
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.base.favorite.FavoriteViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale


class HomeViewModel(
    userService: IUserService,
    plantsRepository: IPlantsRepository,
    favoriteViewModel: FavoriteViewModel,
    navHostController: NavHostController
) : ViewModel() {
    private val _navHostController: NavHostController = navHostController
    private val _plantsRepository: IPlantsRepository = plantsRepository

    private val _favoriteViewModel: FavoriteViewModel = favoriteViewModel
    val favoriteViewModel: FavoriteViewModel = _favoriteViewModel

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

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private var searchQuery: String = ""

    init {
        viewModelScope.launch {
            userService.getUserData()
        }
        fetchPlants()
    }

    fun onPlantTapped(plantId: String){
        _navHostController.navigate("plantDetails/$plantId")
    }

    private fun fetchPlants() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val plantList = _plantsRepository.getAllPlants()
                val categories = _plantsRepository.getPlantsCategories()
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
        if (searchQuery.isNotEmpty()) {
            _plantsFiltered.value = _plantsFiltered.value.filter { plant ->
                plant.name.lowercase(Locale.ROOT) == searchQuery.lowercase()
            }
        }
    }

    fun onSearchInputted(request: String) {
        searchQuery = request
        if (request.isEmpty()) {
            _plantsFiltered.value = _plants.value
        } else {
            _plantsFiltered.value =
                _plants.value.filter { plant ->
                    plant.name.lowercase(Locale.ROOT)
                        .contains(request.lowercase()) &&
                            _selectedCategory.value.lowercase() == plant.category.lowercase()
                }
        }

    }
}
