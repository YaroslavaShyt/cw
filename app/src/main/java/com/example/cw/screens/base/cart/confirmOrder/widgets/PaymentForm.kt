package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.olive

@Composable
fun PaymentForm() {
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = olive,
        unfocusedBorderColor = olive,
        cursorColor = mainText,
        focusedLabelColor = mainText
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { input ->
                    if (input.length <= 19) {
                        cardNumber = input.chunked(4).joinToString(" ")
                    }
                },
                label = { Text("Card number") },
                placeholder = { Text("____ ____ ____ ____") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(250.dp),
                colors = customTextFieldColors
            )

            OutlinedTextField(
                value = expirationDate,
                onValueChange = { input ->
                    if (input.length <= 5) { // Формат MM/YY
                        expirationDate = input
                            .replace(Regex("[^\\d/]"), "")
                            .let {
                                if (it.length == 2 && !it.contains("/")) "$it/" else it
                            }
                    }
                },
                label = { Text("Expires") },
                placeholder = { Text("MM/YY") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.width(90.dp),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(16.dp),

                )

            OutlinedTextField(
                value = cvc,
                onValueChange = { input ->
                    if (input.length <= 3) {
                        cvc = input.replace(Regex("[^\\d]"), "")
                    }
                },
                label = { Text("CVC") },
                placeholder = { Text("___") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.width(90.dp),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(16.dp),


                )
        }
    }
}
