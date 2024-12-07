package com.example.cw.data.plants

class PlantStrings {
    companion object {
        const val NAME: String = "name"
        const val DESCRIPTION: String = "description"
        const val IMAGE: String = "image"
        const val PRICE: String = "price"
        const val CATEGORY: String = "category"
        const val ID: String = "id"
        const val DATA: String = "data"
        const val ABOUT: String = "about"
        const val CARE: String = "care"
        const val WATER: String = "water"
        const val LIGHT: String = "light"
        const val FERTILIZER: String = "fertilizer"
    }
}


data class Plant(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var price: String = "",
    var category: String = "",
    var about: String = "",
    var water: String = "",
    var light: String = "",
    var fertilizer: String = "",
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Plant {
            val dataMap = data[PlantStrings.DATA] as? Map<String, Any> ?: emptyMap()

            return Plant(
                id = data[PlantStrings.ID] as? String ?: "",
                name = dataMap[PlantStrings.NAME] as? String ?: "",
                description = dataMap[PlantStrings.DESCRIPTION] as? String ?: "",
                image = dataMap[PlantStrings.IMAGE] as? String ?: "",
                price = dataMap[PlantStrings.PRICE] as? String ?: "",
                category = dataMap[PlantStrings.CATEGORY] as? String ?: "",
                about = dataMap[PlantStrings.ABOUT] as? String ?: "",
                water = (dataMap[PlantStrings.CARE] as? Map<String, String>?)?.get(PlantStrings.WATER)
                    ?: "",
                light = (dataMap[PlantStrings.CARE] as? Map<String, String>?)?.get(PlantStrings.LIGHT)
                    ?: "",
                fertilizer = (dataMap[PlantStrings.CARE] as? Map<String, String>?)?.get(PlantStrings.FERTILIZER)
                    ?: ""

            )
        }
    }
}