package com.example.cw.screens.base.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.neatGreen

@Composable
fun MainBackButton(onTapped: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(
                neatGreen,
            )
            .pointerInput(Unit) {
                detectTapGestures {
                    onTapped()
                }
            }

    ) {

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = null,
                tint = mainWhite,
                modifier = Modifier.size(40.dp)
            )
        }

    }
}