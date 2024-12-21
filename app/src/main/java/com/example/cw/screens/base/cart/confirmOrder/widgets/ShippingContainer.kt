package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.user.ShippingType
import com.example.cw.ui.theme.olive

@RequiresApi(android.os.Build.VERSION_CODES.O)
@Composable
fun ShippingContainer(
    shippingType: ShippingType,
    isSelected: Boolean,
    select: (ShippingType) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    select(shippingType)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = isSelected,
                onClick = {select(shippingType)},
                colors = RadioButtonDefaults.colors().copy(
                    selectedColor = olive
                )
            )
            Column {
                Text(
                    text = shippingType.shipName + ": " + shippingType.cost,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 2.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = shippingType.getEstimatedShippingDate(),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 2.dp),
                    maxLines = 1
                )
            }
            Image(
                painter = painterResource(id = shippingType.icon),
                contentDescription = null,
                Modifier.padding(start = 6.dp).size(24.dp)
            )
        }
    }
}
