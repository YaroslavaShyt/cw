package com.example.cw.data.services

import android.util.Log
import com.example.cw.data.user.Address
import com.example.cw.data.user.Address.Companion.toMap
import com.example.cw.data.user.User
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import org.koin.core.component.KoinComponent

class UserService(private val userRepository: IUserRepository) : KoinComponent, IUserService {
    override var user: User = User(id = "Oz1zPY0QPS6tKdQzbdsP")

    override suspend fun getUserData() {
        user = userRepository.getUser(user.id)
        Log.d("data", "data ${user.addresses}")
    }

    override suspend fun updateUserAddresses(addresses: List<Address>) {
        user.addresses = addresses

        userRepository.updateUserAddress(
            "Oz1zPY0QPS6tKdQzbdsP",//user.id,
            user.addresses.map { address -> address.toMap() }
        )
    }
}