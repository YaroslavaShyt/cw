package com.example.cw.data.networking

import com.example.cw.domain.user.IAuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import org.koin.core.component.KoinComponent

class AuthRepository(private val firebaseAuth: FirebaseAuth) : IAuthRepository, KoinComponent{

    override suspend fun signInWithGoogle() {
//        val signInIntent = GoogleSignInClient.signInIntent
//        val signInResult: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(signInIntent)
//
//        try {
//            val account = signInResult.await()
//            return firebaseAuthWithGoogle(account)
//        } catch (e: ApiException) {
//            // Обробка помилки авторизації
//            return null
//        }
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

}