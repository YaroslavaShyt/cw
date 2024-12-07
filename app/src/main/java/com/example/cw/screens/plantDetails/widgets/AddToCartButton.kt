package com.example.cw.screens.plantDetails.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

@Composable
fun AddToCartButton(){
    ElevatedButton(

        colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
            containerColor = Color(0xFF475E3E),
            contentColor = Color.White
        ),
        onClick = { /*TODO*/ }) {
        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Add to cart", color = Color.White, fontSize = 25.sp)

            Box(Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFD3B398))
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp).align(Alignment.Center)
                )
            }
        }

    }
}