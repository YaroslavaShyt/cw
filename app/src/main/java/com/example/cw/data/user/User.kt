package com.example.cw.data.user

import com.example.cw.data.user.Address.Companion.toMap
import com.example.cw.data.user.Order.Companion.toMap


private const val idString: String = "id"
private const val addressString: String = "address"
private const val favoriteString: String = "favorite"
private const val dataString: String = "data"
private const val cartString: String = "cart"
private const val nameString: String = "name"
private const val photoString: String = "photo"
private const val settingsString: String = "settings"
const val themeString: String = "theme"
const val localeString: String = "locale"
const val lightTheme: String = "LIGHT"
const val darkTheme: String = "DARK"
const val ukLocale: String = "uk"
const val enLocale: String = "en"


private const val ordersHistoryString: String = "ordersHistory"


data class User(
    val id: String = "",
    val documentId: String = "",
    val name: String = "",
    val photo: String = "",
    var addresses: List<Address> = emptyList(),
    var favorite: List<String> = emptyList(),
    var cart: Map<String, Int> = emptyMap(),
    var ordersHistory: List<Order> = emptyList(),
    var settings: Map<String, String> = emptyMap()
) {
    companion object {
        fun fromMap(data: Map<String, Any>): User {
            val dataMap = data[dataString] as? Map<String, Any> ?: emptyMap()

            return User(
                id = data[idString] as? String ?: "",
                name = dataMap[nameString] as? String ?: "",
                photo = dataMap[photoString] as? String ?: "",
                favorite = dataMap[favoriteString] as List<String>,
                addresses = (dataMap[addressString] as? List<Map<String, Any>>)?.map {
                    Address.fromMap(it)
                } ?: emptyList(),
                cart = dataMap[cartString] as Map<String, Int>,
                ordersHistory = (dataMap[ordersHistoryString] as List<Map<String, Any>>).map {
                    Order.fromMap(it)
                },
                settings = dataMap[settingsString] as Map<String, String>,
            )
        }

        fun User.toMap(): Map<String, Any> {
            return mapOf(
                idString to this.id,
                dataString to mapOf(
                    nameString to this.name,
                    photoString to this.photo,
                    favoriteString to this.favorite,
                    addressString to this.addresses.map { it.toMap() },
                    cartString to this.cart,
                    ordersHistoryString to this.ordersHistory.map { it.toMap() },
                    settingsString to this.settings
                )
            )
        }
    }
}