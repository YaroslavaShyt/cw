package com.example.cw.data.user

import com.example.cw.data.user.User.Companion.toMap
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.userEnd
import com.example.cw.domain.user.IUserRepository
import org.koin.core.component.KoinComponent

private const val addressString: String = "address"
private const val favoritesString: String = "favorite"
private const val cartString: String = "cart"


class UserRepository(private val networkingClient: INetworkingClient) : IUserRepository,
    KoinComponent {

    override suspend fun addUser(user: User) {
        networkingClient.add(userEnd, newData = user.toMap())
    }

    override suspend fun getUser(id: String): User? {
        val userData = networkingClient.getOneById(userEnd, id)
        return if (userData != null) User.fromMap(userData) else null
    }


    override suspend fun updateUserAddress(
        id: String,
        addresses: List<Map<String, Any>>
    ) {
        networkingClient.update(userEnd, id, mapOf(addressString to addresses))
    }

    override suspend fun updateUserFavorites(id: String, favorites: List<String>) {
        networkingClient.update(userEnd, id, mapOf(favoritesString to favorites))
    }

    override suspend fun updateUserCart(id: String, cart: List<String>) {
        networkingClient.update(userEnd, id, mapOf(cartString to cart))
    }


}