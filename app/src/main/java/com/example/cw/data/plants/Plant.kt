package com.example.cw.data.plants

private const val nameString: String = "name"
private const val nameDescription: String = "description"
private const val imageString: String = "image"
private const val priceString: String = "price"
private const val categoryString: String = "category"
private const val idString: String = "id"
private const val dataString: String = "data"


data class Plant(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var price: String = "",
    var category: String = ""
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Plant {
            val dataMap = data[dataString] as? Map<String, Any> ?: emptyMap()

            return Plant(
                id = data[idString] as? String ?: "",
                name = dataMap[nameString] as? String ?: "",
                description = dataMap[nameDescription] as? String ?: "",
                image = dataMap[imageString] as? String ?: "",
                price = dataMap[priceString] as? String?: "",
                category = dataMap[categoryString] as? String ?: "",
            )
        }
    }
}