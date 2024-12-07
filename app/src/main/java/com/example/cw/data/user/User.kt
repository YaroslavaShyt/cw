package com.example.cw.data.user



private const val idString: String = "id"
private const val addressString: String = "address"
private const val favoriteString: String = "favorite"
private const val dataString: String = "data"
private const val cartString: String = "cart"



data class User(
    val id: String = "",
    var addresses: List<Address> = emptyList(),
    var favorite: List<String> = emptyList(),
    var cart: List<String> = emptyList()

) {
    companion object {
        fun fromMap(data: Map<String, Any>): User {
            val dataMap = data[dataString] as? Map<String, Any> ?: emptyMap()

            return User(
                id = data[idString] as? String ?: "",
                favorite = dataMap[favoriteString] as List<String>,
                addresses = (dataMap[addressString] as? List<Map<String, Any>>)?.map {
                    Address.fromMap(
                        it
                    )
                } ?: emptyList(),
                cart = dataMap[cartString] as List<String>,
            )
        }
    }
}