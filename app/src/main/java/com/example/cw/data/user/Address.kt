package com.example.cw.data.user

private const val countryString: String = "country"
private const val cityString: String = "city"
private const val streetString: String = "street"
private const val idString: String = "id"


data class Address(
    var id: String = "",
    var country: String = "",
    var city: String = "",
    var street: String = "",
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Address {
            return Address(
                id = data[idString] as? String ?: "",
                country = data[countryString] as? String ?: "",
                city = data[cityString] as? String ?: "",
                street = data[streetString] as? String ?: "",
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