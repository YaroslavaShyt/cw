package com.example.cw.data.user

private const val countryString: String = "country"
private const val cityString: String = "city"
private const val streetString: String = "street"


data class Address(
    var id: String = "",
    var country: String = "",
    var city: String = "",
    var street: String = "",
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Address {
            return Address(
                country = data[countryString] as? String ?: "",
                city = data[cityString] as? String ?: "",
                street = data[streetString] as? String ?: "",
            )
        }

        fun toMap(country: String, city: String, street: String): Map<String, Any> {
            return mapOf(
                countryString to country,
                cityString to city,
                streetString to street
            )
        }
    }
}