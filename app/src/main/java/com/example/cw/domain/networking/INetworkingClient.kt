package com.example.cw.domain.networking


interface INetworkingClient {
    suspend fun get(
        endpoint: String,
        conditions: Map<String, Any>? = null,
        ids: List<String>? = null,
    ): List<Map<String, Any>>

    suspend fun getOneById(
        endpoint: String,
        id: String,
    ): Map<String, Any>?

    suspend fun update(
        endpoint: String,
        updatedData: Map<String, Any>,
        id: String? = null,
        condition: ((Map<String, Any>) -> Boolean)? = null
    )

    suspend fun add(
        endpoint: String,
        newData: Map<String, Any>
    )
}