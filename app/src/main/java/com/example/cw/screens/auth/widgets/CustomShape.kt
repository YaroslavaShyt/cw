package com.example.cw.screens.auth.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CustomShape : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): androidx.compose.ui.graphics.Outline {
        val path = Path().apply {
            moveTo(0f, size.height * 0.15f) // Початок верхньої дуги
            quadraticBezierTo(
                size.width / 2, 0f, // Верхня середина
                size.width, size.height * 0.15f // Завершення дуги
            )
            lineTo(size.width, size.height + 600) // Правий нижній кут
            lineTo(0f, size.height + 600) // Лівий нижній кут
            close()
        }
        return androidx.compose.ui.graphics.Outline.Generic(path)
    }
}

@Composable
fun CustomShapeContainer() {
    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
        Box(
            modifier = Modifier
                .size(height = 400.dp, width = 800.dp)
                .background(
                    color = Color.White,
                    shape = CustomShape()
                )
        )
    }
}

@Preview
@Composable
fun PreviewCustomShapeContainer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray) // Сірий фон для контрасту
    ) {
        CustomShapeContainer()
    }
}
