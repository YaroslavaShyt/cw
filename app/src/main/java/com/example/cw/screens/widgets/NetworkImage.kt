package com.example.cw.screens.widgets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(url)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}