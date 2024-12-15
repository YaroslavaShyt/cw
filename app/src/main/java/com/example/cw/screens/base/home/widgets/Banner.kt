package com.example.cw.screens.base.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cw.R

@Composable
fun Banner() {
    Box (modifier = Modifier.clip(RoundedCornerShape(50.dp))) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.banner), contentDescription = null
        )
    }
}