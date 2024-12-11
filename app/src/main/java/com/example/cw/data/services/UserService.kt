package com.example.cw.data.services

import android.util.Log
import com.example.cw.data.user.Address
import com.example.cw.data.user.Address.Companion.toMap
import com.example.cw.data.user.User
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import com.google.firebase.auth.FirebaseUser
import org.koin.core.component.KoinComponent

class UserService(private val userRepository: IUserRepository) : KoinComponent, IUserService {
    override var user: User? = null

    override suspend fun initUser(id: String, firebaseUser: FirebaseUser?) {
        user = userRepository.getUser(id)
        if (user == null && firebaseUser != null) {
            val newUser =
                User(
                    id = firebaseUser.uid,
                    name = firebaseUser.displayName.toString(),
                    photo = firebaseUser.photoUrl.toString()
                )
            user = newUser
            userRepository.addUser(newUser)
        }
    }


    override suspend fun getUserData() {
        if (user != null) {
            user = userRepository.getUser(user!!.id)
        }

    }

    override suspend fun updateUserAddresses(addresses: List<Address>) {
        if (user != null) {
            user!!.addresses = addresses

            userRepository.updateUserAddress(
                user!!.id,
                user!!.addresses.map { address -> address.toMap() }
            )
        }

    }

    override suspend fun updateUserFavorites(favorites: List<String>) {
        if (user != null) {
            user!!.favorite = favorites

            userRepository.updateUserFavorites(
                user!!.id,
                user!!.favorite,
            )
        }

    }

    override suspend fun updateUserCart(cart: List<String>) {
        if (user != null) {
            user!!.cart = cart

            userRepository.updateUserCart(
                "Oz1zPY0QPS6tKdQzbdsP",
                user!!.cart,
            )
        }

    }
}