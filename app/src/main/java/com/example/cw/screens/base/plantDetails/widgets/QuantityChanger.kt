package com.example.cw.screens.base.plantDetails.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantityChanger(
    quantity: Int,
    onQuantityPlusTapped: () -> Unit,
    onQuantityMinusTapped: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .width(
                110.dp
            )
            .background(Color(0xffF0F4EF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RoundButton(
                content = "-",
                contentColor = Color(0xFF667085),
                onQuantityTapped = onQuantityMinusTapped
            )
            Text(text = quantity.toString(), fontSize = 18.sp)
            RoundButton(
                content = "+",
                contentColor = Color.White,
                onQuantityTapped = onQuantityPlusTapped
            )

        }
    }
}

@Composable
private fun RoundButton(content: String, contentColor: Color, onQuantityTapped: () -> Unit) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(
                Color(0xFFB5C9AD),
            )
            .pointerInput(Unit) {
                detectTapGestures {
                    onQuantityTapped()

                }
            }

    ) {
        Text(
            text = content,
            color = contentColor,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}