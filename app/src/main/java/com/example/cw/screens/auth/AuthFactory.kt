package com.example.cw.screens.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

class AuthFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build(onGoogleSignInButtonPressed: () -> Unit) {
        AuthScreen(
            AuthViewModel(
                onGoogleSignInButtonPressed,
            ),
            navHostController
        )
    }
}