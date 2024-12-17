package com.example.cw.screens.base.plantDetails.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.commonGray
import com.example.cw.ui.theme.wideGreen

@Composable
fun CareComponent(label: String, value: String, image: Int) {
    Row(Modifier.width(130.dp)) {
        Image(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp),
            painter = painterResource(id = image), contentDescription = null
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                value,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = wideGreen,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp

                )
            )
            Text(
                label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = commonGray,
                    fontWeight = FontWeight.W600,
                    fontSize = 10.sp
                )
            )
        }
    }
}