package com.example.cw.screens.base.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.plants.Plant
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.ui.theme.like
import com.example.cw.ui.theme.mainCard
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.neatGreen
import com.example.cw.ui.theme.olive
import com.example.cw.ui.theme.unlike

@Composable
fun PlantItem(plant: Plant, isLiked: Boolean = false, onLikeTapped: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(260.dp)
            .width(160.dp),
        colors = CardColors(
            containerColor = mainCard,
            disabledContainerColor = mainCard,
            contentColor = mainText,
            disabledContentColor = mainText,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = neatGreen,
                        shape = CircleShape
                    )
                    .border(2.dp, neatGreen, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "favorite",
                    tint = if (isLiked) like else unlike,
                    modifier = Modifier
                        .padding(4.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { _ ->
                                    onLikeTapped()
                                },
                            )
                        }
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
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
                    fontSize = 16.sp
                )
            )

            Box(
                modifier = Modifier
                    .width(130.dp)
                    .background(
                        color = mainWhite,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(2.dp, mainWhite, shape = RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$ " + plant.price,
                        style = MaterialTheme.typography.titleLarge.copy(color = olive),
                        modifier = Modifier.padding(end = 10.dp),
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                color = neatGreen,
                                shape = CircleShape
                            )
                            .border(2.dp, neatGreen, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart, contentDescription = "buy",
                            tint = olive,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}



