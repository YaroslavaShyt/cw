package com.example.cw.domain.user

import com.example.cw.data.user.User
import org.koin.core.component.KoinComponent

interface IUserRepository : KoinComponent {
    suspend fun addUser(user: User)

    suspend fun getUser(id: String): User?

    suspend fun updateUserAddress(
        id: String,
        addresses: List<Map<String, Any>>
    )

    suspend fun updateUserFavorites(
        id: String,
        favorites: List<String>
    )

    suspend fun updateUserCart(
        id: String,
        cart: Map<String, Any>
    )
}