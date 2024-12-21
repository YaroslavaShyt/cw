package com.example.cw.screens.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(
    onGoogleSignInButtonPressed: () -> Unit,
) :
    ViewModel() {
    val onSignInButtonPressed: () -> Unit = onGoogleSignInButtonPressed

    val isContinueAsGuest = MutableStateFlow(false)
    val isContinueAsGuestState: StateFlow<Boolean> = isContinueAsGuest
}