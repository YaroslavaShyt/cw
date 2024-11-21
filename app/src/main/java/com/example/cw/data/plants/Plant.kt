package com.example.cw.data.plants

private const val nameString: String = "name"
private const val nameDescription: String = "description"


data class Plant(
    var name: String = "",
    var description: String = ""
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Plant {
            return Plant(
                name = data[nameString] as? String ?: "",
                description = data[nameDescription] as? String ?: ""
            )
        }
    }
}