package com.example.cw.data.plants

private const val nameString: String = "name"
private const val nameDescription: String = "description"
private const val imageString: String = "image"
private const val priceString: String = "price"
private const val categoryString: String = "category"


data class Plant(
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var price: String = "",
    var category: String = ""
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Plant {
            return Plant(
                name = data[nameString] as? String ?: "",
                description = data[nameDescription] as? String ?: "",
                image = data[imageString] as? String ?: "",
                price = data[priceString] as? String ?: "",
                category = data[categoryString] as? String ?: "",
            )
        }
    }
}