package com.example.cw.screens.auth

import androidx.lifecycle.ViewModel

class AuthViewModel(onGoogleSignInButtonPressed: () -> Unit) : ViewModel() {
    val onSignInButtonPressed: () -> Unit = onGoogleSignInButtonPressed
}