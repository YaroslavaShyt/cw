package com.example.cw.screens.base.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.plants.Plant
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(userService: IUserService, plantsRepository: IPlantsRepository) :
    ViewModel() {
    private val _userService: IUserService = userService
    private val _plantsRepository: IPlantsRepository = plantsRepository

    private val _likedPlants = MutableStateFlow<List<Plant>>(emptyList())
    val likedPlants: StateFlow<List<Plant>> = _likedPlants

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        if (userService.user.value != null) {
            viewModelScope.launch {
                if (userService.user.value!!.favorite.isNotEmpty()) {
                    _likedPlants.value =
                        _plantsRepository.getPlantsById(ids = userService.user.value!!.favorite)
                }
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