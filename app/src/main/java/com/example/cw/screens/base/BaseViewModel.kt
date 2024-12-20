package com.example.cw.screens.base

import android.content.Context
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.handlers.LocalizationHandler
import com.example.cw.data.user.localeString
import com.example.cw.data.user.themeString
import com.example.cw.domain.handler.ILocalizationHandler
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class BaseViewModel(userService: IUserService, authService: IAuthService, context: Context) : ViewModel(),
    KoinComponent {
    private val languageHandler  = LocalizationHandler(context, userService)
    val currentLanguage = userService.user.value?.settings?.get(localeString)

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    private val _userPhoto = MutableStateFlow<String?>(null)
    val userPhoto: StateFlow<String?> = _userPhoto


    init {
        viewModelScope.launch {
            authService.user.value?.let { userService.initUser(it.uid) }
            _userName.value = userService.user.value?.name
            _userPhoto.value = userService.user.value?.photo
        }
    }

    fun changeLanguage(language: String) {
        languageHandler.changeLanguage(language)
    }

    fun setLocale() {
        languageHandler.setLocale(currentLanguage)
    }

    fun cleanData() {
        _userName.value = null
        _userPhoto.value = null
    }

}