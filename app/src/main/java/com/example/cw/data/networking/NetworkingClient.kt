package com.example.cw.data.networking

import com.example.cw.domain.networking.INetworkingClient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import org.koin.core.component.KoinComponent

class NetworkingClient(private val firebaseFireStore: FirebaseFirestore) : INetworkingClient,
    KoinComponent {

    override suspend fun get(
        endpoint: String,
        conditions: Map<String, Any>?
    ): List<Map<String, Any>> {
        return try {
            val query = firebaseFireStore.collection(endpoint).apply {
                conditions?.forEach { (field, value) ->
                    this.whereEqualTo(field, value)
                }
            }

            val result: QuerySnapshot = query.get().await()

            result.documents.map { document ->
                document.data ?: emptyMap()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
