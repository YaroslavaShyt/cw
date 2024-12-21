package com.example.cw.screens.base.cart.confirmOrder.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.DarkGrey

@Composable
fun OrderOnTheWayPlaceholder(
    onTextButtonTapped: ()->Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.shipping), contentDescription = null
            )

            Text(
                text = "Your parcel",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "is on the way!",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                modifier = Modifier.padding(top = 5.dp)
            )
            TextButton(onClick = { onTextButtonTapped() }) {
                Text(
                    text = "To purchases",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        color = DarkGrey
                    ),
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
        }
        ConfettiAnimation(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun Show() {
    CwTheme {
        Column {

        }
    }
}