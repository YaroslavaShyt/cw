package com.example.cw.data.user


private const val idString: String = "id"
private const val addressString: String = "address"


data class User(
    val id: String = "",
    val addresses: List<Address> = emptyList()
) {
    companion object {
        fun fromMap(data: Map<String, Any>): User {
            return User(
                id = data[idString] as? String ?: "",
                addresses = (data[addressString] as? List<Map<String, Any>>)?.map {
                    Address.fromMap(
                        it
                    )
                } ?: emptyList()
            )
        }
    }
}