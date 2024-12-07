package com.example.cw.data.user

class OrderStrings {
    companion object {
        const val CODE: String = "code"
        const val DATE: String = "date"
        const val STATUS: String = "status"
        const val PLANTS: String = "plants"

    }
}


data class Order(
    val code: String = "",
    val date: String = "",
    val status: String = "",
    val plants: List<String> = emptyList()
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Order {
            return Order(
                code = data[OrderStrings.CODE] as? String ?: "",
                date = data[OrderStrings.DATE] as? String ?: "",
                status = data[OrderStrings.STATUS] as? String ?: "",
                plants = data[OrderStrings.PLANTS] as? List<String> ?: emptyList(),
            )
        }
    }
}
