package com.example.cw

private const val  nameString: String = "name"
private const val  nameDescription: String = "description"



data class Plant(
    var name: String = "",
    var description: String = ""
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Plant {
            val name = data[nameString] as? String ?: ""
            val description = data[nameDescription] as? String ?: ""
            return Plant(name, description)
        }
    }
}
