package com.example.cw.domain.networking

import org.koin.core.component.KoinComponent

interface INetworkingClient: KoinComponent {
    suspend fun get(endpoint: String, conditions: Map<String, Any>? = null): List<Map<String, Any>>
    suspend fun update(
        endpoint: String,
        id: String,
        updatedData: Map<String, Any>
    )
}