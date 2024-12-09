package com.example.cw.domain.services

import org.koin.core.component.KoinComponent

interface IAuthService: KoinComponent {
    suspend fun signInWithGoogle()
    suspend fun signOut()
}