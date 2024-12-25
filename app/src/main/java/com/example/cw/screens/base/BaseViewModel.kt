package com.example.cw.screens.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cw.core.routing.addressesRoute
import com.example.cw.core.routing.cartRoute
import com.example.cw.core.routing.ordersHistoryRoute
import com.example.cw.data.handlers.LocalizationHandler
import com.example.cw.data.user.localeString
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.auth.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class BaseViewModel(
    userService: IUserService,
    authService: IAuthService,
    private val onAuth: () -> Unit,
    private val authViewModel: AuthViewModel,
    private val navController: NavHostController
) :
    ViewModel() {
    private val languageHandler = LocalizationHandler(userService)
    val currentLanguage =
        MutableStateFlow("en")

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    private val _userPhoto = MutableStateFlow<String?>(null)
    val userPhoto: StateFlow<String?> = _userPhoto


    init {
        viewModelScope.launch {
            authService.user.value?.let { userService.initUser(it.uid) }
            _userName.value = userService.user.value?.name
            _userPhoto.value = userService.user.value?.photo
            currentLanguage.value = userService.user.value?.settings?.get(localeString) ?: "en"
        }
    }

    fun onOrderHistoryTapped(){
        navController.navigate(ordersHistoryRoute)
    }

    fun onAuthButtonTapped(){
        onAuth()
    }

    fun onAddressTapped(){
        navController.navigate(route = addressesRoute)

    }

    fun onCartTapped(){
        navController.navigate(cartRoute)
    }

    fun changeLanguage(context: Context, language: String) {
        viewModelScope.launch {
            languageHandler.changeLocale(context, language, true)
            currentLanguage.value = language
        }
    }


    fun cleanData() {
        _userName.value = null
        _userPhoto.value = null
    }

}