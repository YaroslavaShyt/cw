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
import com.example.cw.R
import com.example.cw.data.plants.Plant
import com.example.cw.ui.theme.mainText

@Composable
fun DetailsContainer(
    plant: Plant,
    quantity: Int,
    onQuantityPlusTapped: () -> Unit,
    onQuantityMinusTapped: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                .height(700.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 20.dp
                    )
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
                text = "Care",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = mainText,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                CareComponent(label = "WATER", value = plant.water, image = R.drawable.drop)
                CareComponent(label = "LIGHT", value = plant.light, image = R.drawable.sun)
                CareComponent(
                    label = "FERTILIZER",
                    value = plant.fertilizer,
                    image = R.drawable.fertilizer
                )
            }
            AboutWidget(description = plant.about)
        }

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