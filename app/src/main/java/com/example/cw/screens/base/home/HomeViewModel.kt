package com.example.cw.screens.base.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.base.favorite.FavoriteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale


class HomeViewModel(
    private val userService: IUserService,
    plantsRepository: IPlantsRepository,
    favoriteViewModel: FavoriteViewModel,
    navHostController: NavHostController,
    private val onAuth: () -> Unit,
) : ViewModel() {
    private val _navHostController: NavHostController = navHostController
    val navHostController = _navHostController
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

    private val _isDetails = MutableStateFlow(false)
    val isDetails: StateFlow<Boolean> = _isDetails

    private val _tappedPlantID = MutableStateFlow<String>("")
    val tappedPlantID: StateFlow<String> = _tappedPlantID


    private var searchQuery: String = ""

    init {
        viewModelScope.launch {
            try {
                _loading.value = true
                delay(200)
                val plants = _plantsRepository.getAllPlants()
                val categories = _plantsRepository.getPlantsCategories()

                _plants.value = plants
                _plantsFiltered.value = plants
                _categories.value = categories

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    fun authorize(){
        onAuth()
    }

    fun hideDetails(){
        _isDetails.value = false
        _tappedPlantID.value = ""
    }

    fun onPlantTapped(plantId: String) {
        _tappedPlantID.value = plantId
        _isDetails.value = true
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
            if (_selectedCategory.value == "All") {
                _plantsFiltered.value = _plants.value
            } else {
                _plantsFiltered.value = _plants.value.filter { plant ->
                    plant.category.lowercase() == _selectedCategory.value.lowercase()
                }
            }
        } else {
            _plantsFiltered.value =
                _plants.value.filter { plant ->
                    plant.name.lowercase(Locale.ROOT)
                        .contains(request.lowercase())
                            &&
                            (_selectedCategory.value.lowercase() == "all" ||
                                    _selectedCategory.value.lowercase() == plant.category.lowercase()
                                    )

                }
        }
    }
}
