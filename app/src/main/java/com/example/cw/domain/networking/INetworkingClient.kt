package com.example.cw.domain.networking

interface INetworkingClient {
    suspend fun get(endpoint: String): List<Map<String, Any>>

}