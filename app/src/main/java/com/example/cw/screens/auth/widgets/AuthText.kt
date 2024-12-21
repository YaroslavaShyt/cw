package com.example.cw.screens.auth.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.DarkGrey
import com.example.cw.ui.theme.mainText

@Composable
fun AuthText() {
    Box(Modifier.height(200.dp)) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Shop anywhere and",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = DarkGrey,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800
                ),
            )
            Text(
                text = "everywhere with",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = DarkGrey,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800
                ),
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "GreenLife",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = mainText,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.W800
                ),
            )
        }
    }
}