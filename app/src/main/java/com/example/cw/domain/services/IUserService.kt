package com.example.cw.domain.services

import com.example.cw.data.user.User
import org.koin.core.component.KoinComponent

interface IUserService: KoinComponent {
    var user: User
    suspend fun getUserData()
}