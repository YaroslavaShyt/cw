package com.example.cw.data.user

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cw.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class ShippingType(val shipName: String, val cost: String, val estimationDate: Int, val icon: Int) {
    Standard("Standard", "free", 5, R.drawable.plane),
    Fast("Fast", "20$", 2, R.drawable.send);

    @RequiresApi(Build.VERSION_CODES.O)
    fun getEstimatedShippingDate(): String {
        val currentDate = LocalDate.now()
        val estimatedDate = currentDate.plusDays(estimationDate.toLong())

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return estimatedDate.format(formatter)
    }
}

