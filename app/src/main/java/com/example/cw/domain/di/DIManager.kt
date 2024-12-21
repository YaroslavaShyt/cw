package com.example.cw.domain.di

import com.example.cw.data.networking.NetworkingClient
import com.example.cw.data.plants.PlantsRepository
import com.example.cw.data.services.AuthService
import com.example.cw.data.services.UserService
import com.example.cw.data.user.UserRepository
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import com.google.firebase.firestore.FirebaseFirestore


object AppContainer {

    private val firebaseFireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val networkingClient: INetworkingClient by lazy {
        NetworkingClient(firebaseFireStore)
    }

    fun plantsRepository(): IPlantsRepository {
        return PlantsRepository(networkingClient)
    }

    private fun userRepository(): IUserRepository {
        return UserRepository(networkingClient)
    }

    val userService: IUserService by lazy {
        UserService(userRepository())
    }

    val authService: IAuthService by lazy {
        AuthService(userService)
    }
}

