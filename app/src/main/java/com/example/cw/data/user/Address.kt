package com.example.cw.data.user

private const val countryString: String = "country"
private const val cityString: String = "city"
private const val streetString: String = "street"
private const val dataString: String = "data"
private const val idString: String = "id"


data class Address(
    var id: String = "",
    var country: String = "",
    var city: String = "",
    var street: String = "",
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Address {
            val dataMap = data[dataString] as? Map<String, Any> ?: emptyMap()

            return Address(
                id = data[idString] as? String ?: "",
                country = dataMap[countryString] as? String ?: "",
                city = dataMap[cityString] as? String ?: "",
                street = dataMap[streetString] as? String ?: "",
            )
        }

        fun Address.toMap(): Map<String, String> {
            return mapOf(
                countryString to this.country,
                cityString to this.city,
                streetString to this.street
            )
        }
    }
}