package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.olive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentForm(
    cardNumber: String,
    onCardNumberInputted: (String) -> Unit,
    cvc: String,
    onCVVInputted: (String) -> Unit,
    expirationDate: String,
    onExpirationDateInputted: (String) -> Unit,
) {

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = olive,
        unfocusedBorderColor = olive,
        cursorColor = mainText,
        focusedLabelColor = mainText
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
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
                    if (input.length < 17)
                        onCardNumberInputted(input)
                },
                textStyle = MaterialTheme.typography.bodySmall,
                label = {
                    Text(
                        stringResource(id = R.string.card_number),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = {
                    Text(
                        "____ ____ ____ ____",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(250.dp),
                colors = customTextFieldColors
            )

            OutlinedTextField(
                value = expirationDate,
                onValueChange = { input ->
                    if (input.length < 6)
                        onExpirationDateInputted(input)
                },
                textStyle = MaterialTheme.typography.bodySmall,
                label = {
                    Text(
                        stringResource(id = R.string.expires),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                placeholder = { Text("MM/YY", style = MaterialTheme.typography.bodySmall) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                singleLine = true,
                modifier = Modifier.width(90.dp),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(16.dp),
            )

            OutlinedTextField(
                value = cvc,
                onValueChange = { input ->
                    if (input.length < 4)
                        onCVVInputted(input)
                },
                textStyle = MaterialTheme.typography.bodySmall,
                label = { Text("CVC", style = MaterialTheme.typography.bodySmall) },
                placeholder = { Text("___", style = MaterialTheme.typography.bodySmall) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.width(90.dp),
                colors = customTextFieldColors,
                shape = RoundedCornerShape(16.dp),
            )
        }
    }
}
