package com.example.cw.screens

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import kotlinx.coroutines.launch


class MainViewModel(private val _authService: IAuthService, private val _userService: IUserService) : ViewModel(), KoinComponent {
    private val _user = MutableStateFlow(_authService.user)
    val user: StateFlow<FirebaseUser?> = _user


    @Composable
    fun getAuthFlow(): ManagedActivityResultLauncher<Intent, ActivityResult> {
        return _authService.authFlow(onAuthSuccess = { onAuthSuccess(it) }, onAuthFailure = {})
    }

    fun onAuthSuccess(user: FirebaseUser) {
        _user.value = user
        viewModelScope.launch {
            _user.value?.let { _userService.initUser(it.uid) }
        }
    }


    fun getSignInClient(token: String, context: Context): GoogleSignInClient? {
        return _authService.signInWithGoogle(token, context)
    }

    fun onLogout() {
        viewModelScope.launch {
            _authService.signOut()
            _user.value = null
        }
    }
}