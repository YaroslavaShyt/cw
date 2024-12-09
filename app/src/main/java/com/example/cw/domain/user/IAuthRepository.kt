package com.example.cw.domain.user

import org.koin.core.component.KoinComponent

interface IAuthRepository : KoinComponent {
    suspend fun signInWithGoogle()
    suspend fun signOut()
}