package com.example.cw.data.user

import com.example.cw.data.plants.Plant
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.plantsEnd
import com.example.cw.domain.networking.userEnd
import com.example.cw.domain.user.IUserRepository
import org.koin.core.component.KoinComponent

private const val  addressString: String = "address"

class UserRepository(private val networkingClient: INetworkingClient) : IUserRepository,
    KoinComponent {

    override suspend fun getUserAddresses(field: String, value: Any): List<Address> {
        val conditions = mapOf(field to value)
        val addressesData = networkingClient.get(userEnd, conditions)[0][addressString]
                as List<Map<String, String>>

        return addressesData.map { addressData ->
            Address.fromMap(addressData)
        }
    }
}