package com.example.cw.screens.base.cart.confirmOrder.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.inactive
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.olive

@Composable
fun ConfirmOrderButton(
    isActive: Boolean,
    onPressed: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier
            .height(56.dp)
            .width(270.dp),
        colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
            containerColor = if (isActive) olive else inactive,
            contentColor = mainWhite
        ),
        onClick = { onPressed() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Confirm",
                color = mainWhite,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 20.dp)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFD3B398))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = "Shopping Card",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }

    }
}