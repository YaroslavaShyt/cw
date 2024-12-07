package com.example.cw.screens.home.favorite

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

class FavoriteViewModel(userService: IUserService, plantsRepository: IPlantsRepository) :
    ViewModel(), KoinComponent {
    private val _userService: IUserService = userService
    private val _plantsRepository: IPlantsRepository = plantsRepository

    private val _likedPlants = MutableStateFlow<List<Plant>>(emptyList())
    val likedPlants: StateFlow<List<Plant>> = _likedPlants

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            if (userService.user.favorite.isNotEmpty()) {
                _likedPlants.value =
                    _plantsRepository.getPlantsById(ids = userService.user.favorite)
            }
        }
    }

    fun onLikeTapped(plant: Plant) {
        if (_likedPlants.value.contains(plant)) {
            _likedPlants.value -= plant
        } else {
            _likedPlants.value += plant
        }
        viewModelScope.launch {
            _userService.updateUserFavorites(_likedPlants.value.map { plant ->
                plant.id
            })
        }
    }

}