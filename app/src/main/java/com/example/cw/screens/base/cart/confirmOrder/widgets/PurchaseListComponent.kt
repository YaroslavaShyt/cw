package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.data.plants.Plant
import com.example.cw.ui.theme.mainCard
import com.example.cw.ui.theme.mainText

@Composable
fun PurchaseListComponent(plant: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(120.dp)
            .width(80.dp),
        colors = CardColors(
            containerColor = mainCard,
            disabledContainerColor = mainCard,
            contentColor = mainText,
            disabledContentColor = mainText,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .align(Alignment.End)
        ) {

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    NetworkImage(url = plant.image)
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = plant.name,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 10.sp
                    )
                )

            }
        }
    }
}