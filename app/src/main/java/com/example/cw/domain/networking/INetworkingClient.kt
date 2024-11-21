package com.example.cw.domain.networking

import org.koin.core.component.KoinComponent

interface INetworkingClient: KoinComponent {
    suspend fun get(endpoint: String): List<Map<String, Any>>

}