package com.example.cw.screens.auth

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class AuthViewModel(onGoogleSignInButtonPressed: () -> Unit) : ViewModel(), KoinComponent {
    val onSignInButtonPressed: () -> Unit = onGoogleSignInButtonPressed
}