package com.example.cw.data.user

import com.example.cw.data.user.User.Companion.toMap
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.userEnd
import com.example.cw.domain.user.IUserRepository

private const val addressString: String = "address"
private const val favoritesString: String = "favorite"
private const val cartString: String = "cart"


class UserRepository(private val networkingClient: INetworkingClient) : IUserRepository {

    override suspend fun addUser(user: User) {
        networkingClient.add(userEnd, newData = user.toMap())
    }

    override suspend fun getUser(id: String): User? {
        val userData = networkingClient.getOneById(userEnd, id)
        return if (userData != null) User.fromMap(userData) else null
    }

    override suspend fun updateUserOrder(id: String, order: Map<String, Any>) {
        networkingClient.update(
            endpoint = userEnd,
            updatedData = order,
            condition = { data -> data["id"] == id }
        )
    }


    override suspend fun updateUserAddress(
        id: String,
        addresses: List<Map<String, Any>>
    ) {
        networkingClient.update(userEnd, id = id, updatedData = mapOf(addressString to addresses))
    }

    override suspend fun updateUserFavorites(id: String, favorites:  Map<String, Any>) {
        networkingClient.update(
            endpoint= userEnd,
            updatedData = favorites,
            condition = { data -> data["id"] == id }
        )
    }

    override suspend fun updateUserCart(id: String, cart: Map<String, Any>) {
        networkingClient.update(
            endpoint = userEnd,
            updatedData = cart,
            condition = { data -> data["id"] == id }
        )
    }

    override suspend fun updateUserSettings(
        id: String,
        setting: Map<String, Any>
    ) {
        networkingClient.update(
            endpoint = userEnd,
            updatedData = setting,
            condition = { data -> data["id"] == id })
    }

}