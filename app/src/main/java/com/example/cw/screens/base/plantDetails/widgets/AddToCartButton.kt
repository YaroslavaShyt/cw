package com.example.cw.screens.base.plantDetails.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.mainWhite

@Composable
fun AddToCartButton(
    isAddedToCart: Boolean,
    onPressed: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier
            .height(56.dp)
            .width(if (isAddedToCart) 180.dp else 300.dp),
        colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
            containerColor = Color(0xFF475E3E),
            contentColor = Color.White
        ),
        onClick = { onPressed() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (isAddedToCart) "To cart" else "Add to cart",
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
                Icon(
                    imageVector = if (isAddedToCart) Icons.Outlined.Check
                    else Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }

    }
}