package com.example.cw.screens.base.plantDetails.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.blueGray
import com.example.cw.ui.theme.mainText

@Composable
fun AboutWidget(description: String) {
    Column {
        Text(
            text = stringResource(id = R.string.about_plant),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = mainText,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = description,
            fontSize = 13.sp,
            fontWeight = FontWeight.W200,
            color = blueGray
        )
    }

}