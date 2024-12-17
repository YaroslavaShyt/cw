package com.example.cw.screens.base.plantDetails.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.blueGray
import com.example.cw.ui.theme.mainText

@Composable
fun AboutWidget(description: String) {
    Column {
        Text(
            text = "About plant",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = mainText,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text =
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
//description,
            fontSize = 13.sp,
            fontWeight = FontWeight.W200,
            color = blueGray
        )
    }

}