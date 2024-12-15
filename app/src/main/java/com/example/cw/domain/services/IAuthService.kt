package com.example.cw.domain.services

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface IAuthService : KoinComponent {
    var user: StateFlow<FirebaseUser?>

    fun signInWithGoogle(token: String, context: Context): GoogleSignInClient?

    suspend fun  signOut()

    @Composable
    fun authFlow(
        onAuthSuccess: (user: FirebaseUser) -> Unit,
        onAuthFailure: () -> Unit
    ): ManagedActivityResultLauncher<Intent, ActivityResult>
}