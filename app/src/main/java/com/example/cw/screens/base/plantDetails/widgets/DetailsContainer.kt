package com.example.cw.screens.base.plantDetails.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.plants.Plant

@Composable
fun DetailsContainer(
    plant: Plant,
    quantity: Int,
    onQuantityPlusTapped: () -> Unit,
    onQuantityMinusTapped: () -> Unit
) {

    Column(

        modifier = Modifier
            .height((LocalConfiguration.current.screenHeightDp / 1).dp)
            .background(Color.White)
            .clip(RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = plant.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = plant.price + "$",
                    color = Color(0xFF475E3E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )
            }
            QuantityChanger(
                quantity = quantity,
                onQuantityPlusTapped,
                onQuantityMinusTapped
            )
        }
        Text(
            text = "About plant",
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        )
        Text(
            text = plant.about,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Gray
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AddToCartButton()
        }
    }

}