package com.example.cw.screens.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class BaseViewModel(userService: IUserService) : ViewModel(), KoinComponent{
    var userName : String? = userService.user?.name
    var userPhoto : String? = userService.user?.photo



}