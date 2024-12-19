package com.example.cw.screens.base.cart.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.data.plants.Plant
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.screens.base.plantDetails.widgets.QuantityChanger
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.like
import com.example.cw.ui.theme.mainCard
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.moreGray
import com.example.cw.ui.theme.neatGreen
import com.example.cw.ui.theme.olive
import com.example.cw.ui.theme.unlike

@Composable
fun CartPlantComponent(
    plant: Plant,
    quantity: Int,
    isLiked: Boolean = false,
    onLikeTapped: () -> Unit = {},
    onQuantityPlusTapped: () -> Unit = {},
    onQuantityMinusTapped: () -> Unit = {},
    onDeleteButtonTapped: () -> Unit = {},
) {
    Row(
        Modifier
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(mainCard)
        ) {
            Row(
                Modifier
                    .padding(8.dp)
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BuildImage(plant = plant)

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .width(130.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 8.dp)
                            .width(130.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        Box(
                            modifier = Modifier
                                .align(Alignment.End)
                        ) {
                            QuantityChanger(
                                quantity = quantity,
                                onQuantityPlusTapped = { onQuantityPlusTapped() },
                                onQuantityMinusTapped = { onQuantityMinusTapped() }
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight()
                                .width(130.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = plant.name,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 16.sp
                                )
                            )
                            BuildPrice(plant = plant)

                        }
                        BuildLikeButton(onLikeTapped = { onLikeTapped() }, isLiked = isLiked)

                    }
                }
            }
        }
        DeleteButton(onDeleteButtonTapped)
    }
}


@Composable
private fun DeleteButton(onDeleteButtonTapped: () -> Unit) {
    Box(modifier = Modifier.padding(start = 6.dp)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(moreGray)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onDeleteButtonTapped()
                    }
                }
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = mainText.copy(alpha = 0.6f),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            )
        }
    }
}

@Composable
private fun BuildImage(plant: Plant) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(170.dp)
            .padding(end = 8.dp)
    ) {
        NetworkImage(url = plant.image)
    }
}

@Composable
private fun BuildPrice(plant: Plant) {
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
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "buy",
                    tint = olive,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}

@Composable
private fun BuildLikeButton(onLikeTapped: () -> Unit, isLiked: Boolean) {
    Column {
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
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
                            detectTapGestures(onTap = { onLikeTapped() })
                        }
                )
            }
        }
    }
}

