package com.example.cw.data.networking

import com.example.cw.domain.networking.INetworkingClient
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.koin.core.component.KoinComponent

private const val idString: String = "id"
private const val dataString: String = "data"

class NetworkingClient(private val firebaseFireStore: FirebaseFirestore) : INetworkingClient,
    KoinComponent {

    override suspend fun add(
        endpoint: String,
        newData: Map<String, Any>
    ) {
        try {
            firebaseFireStore.collection(endpoint).add(newData).await()
        } catch (e: Exception) {
            throw Exception("Error adding document: ${e.localizedMessage}")
        }
    }

    override suspend fun get(
        endpoint: String,
        conditions: Map<String, Any>?,
        ids: List<String>?
    ): List<Map<String, Any>> {
        return try {
            if (!ids.isNullOrEmpty()) {
                val result = firebaseFireStore.collection(endpoint)
                    .whereIn(FieldPath.documentId(), ids)
                    .get()
                    .await()

                result.documents.map { document ->
                    val documentData = document.data ?: emptyMap()
                    val documentId = document.id
                    mapOf(idString to documentId, dataString to documentData)
                }
            } else {
                conditions?.let {
                    val query = firebaseFireStore.collection(endpoint).apply {
                        it.forEach { (field, value) ->
                            this.whereEqualTo(field, value)
                        }
                    }
                    val result = query.get().await()
                    result.documents.map { document ->
                        val documentData = document.data ?: emptyMap()
                        val documentId = document.id
                        mapOf(idString to documentId, dataString to documentData)
                    }
                } ?: run {
                    val documents = firebaseFireStore.collection(endpoint).get().await()
                    documents.map { document ->
                        val documentData = document.data
                        val documentId = document.id
                        mapOf(idString to documentId, dataString to documentData)
                    }
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


    override suspend fun update(
        endpoint: String,
        id: String,
        updatedData: Map<String, Any>
    ) {
        try {
            val documentRef = firebaseFireStore.collection(endpoint).document(id)
            documentRef.update(updatedData).await()
        } catch (e: Exception) {
            throw Exception("Error updating document: ${e.localizedMessage}")
        }
    }
}
