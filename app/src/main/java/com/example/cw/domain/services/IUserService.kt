package com.example.cw.domain.services

import com.example.cw.data.user.Address
import com.example.cw.data.user.User
import com.google.firebase.auth.FirebaseUser
import org.koin.core.component.KoinComponent

interface IUserService: KoinComponent {
    var user: User?
    suspend fun initUser(id: String, firebaseUser: FirebaseUser? = null)
    suspend fun getUserData()
    suspend fun updateUserAddresses(addresses: List<Address>)
    suspend fun updateUserFavorites(favorites: List<String>)
    suspend fun updateUserCart(cart: List<String>)
}