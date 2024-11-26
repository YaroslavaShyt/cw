package com.example.cw.domain.user

import com.example.cw.data.user.Address
import org.koin.core.component.KoinComponent

interface IUserRepository: KoinComponent {
    suspend fun getUserAddresses(field: String, value: Any): List<Address>
}