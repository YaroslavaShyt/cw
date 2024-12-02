package com.example.cw.domain.user

import com.example.cw.data.user.Address
import com.example.cw.data.user.User
import org.koin.core.component.KoinComponent

interface IUserRepository : KoinComponent {
    suspend fun getUser(id: String): User

    suspend fun updateUserAddress(
        id: String,
        addresses: List<Map<String, Any>>
    )
}