package com.example.cw.core.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(url)

    Box(Modifier.clip(RoundedCornerShape(10.dp))) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier
        )
    }
}