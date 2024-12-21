package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.user.Address
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.moreGray
import com.example.cw.ui.theme.olive

@Composable
fun AddressContainer(isSelected: Boolean, address: Address, select: (Address) -> Unit) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .padding(end = 8.dp)
            .border(
                border = BorderStroke(2.dp, if (isSelected) olive else moreGray),
                shape = RoundedCornerShape(14.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures {
                    select(address)
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = address.country,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                modifier = Modifier.padding(bottom = 2.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = address.city,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 2.dp),
                maxLines = 1
            )
            Text(
                text = address.street,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                modifier = Modifier.padding(bottom = 2.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun Build() {
    CwTheme {
        Column(Modifier.background(mainWhite)) {
            AddressContainer(
                isSelected = true,
                address = Address(
                    "Kyi", "Kyiv", "Vinnytsia", "Lypovetska 600 khksdfsdfsdfsdfjhkhjkh"
                ),
                {}
            )
        }

    }
}