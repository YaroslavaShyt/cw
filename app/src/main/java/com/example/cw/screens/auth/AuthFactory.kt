package com.example.cw.screens.auth

import androidx.compose.runtime.Composable

class AuthFactory {

    @Composable
    fun Build(onGoogleSignInButtonPressed: () -> Unit) {
        AuthScreen(
            AuthViewModel(
                onGoogleSignInButtonPressed
            )
        )
    }
}