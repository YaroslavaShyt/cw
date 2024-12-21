package com.example.cw.data.user

import com.example.cw.R

enum class ShippingType(val shipName: String, val cost: String, val estimationDate: String, val icon: Int) {
    Standard("Standard", "free", "October 13-16",  R.drawable.plane),
    Fast("Fast", "20$", "October 10-12", R.drawable.send)
}
