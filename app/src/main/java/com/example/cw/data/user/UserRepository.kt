package com.example.cw.data.user

import com.example.cw.data.plants.Plant
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.plantsEnd
import com.example.cw.domain.networking.userEnd
import com.example.cw.domain.user.IUserRepository
import org.koin.core.component.KoinComponent

private const val addressString: String = "address"

class UserRepository(private val networkingClient: INetworkingClient) : IUserRepository,
    KoinComponent {

    override suspend fun getUser(id: String): User {
        val conditions = mapOf("id" to id)

        val userData = networkingClient.get(userEnd, conditions).firstOrNull()
            ?: throw Exception("User not found")

        return User.fromMap(userData)
    }

    override suspend fun addUserAddress(
        address: Map<String, String>
    ) {

    }

    override suspend fun updateUserAddress(address: Address) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserAddress(address: Address) {
        TODO()
    }
}