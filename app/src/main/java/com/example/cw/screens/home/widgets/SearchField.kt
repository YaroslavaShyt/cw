package com.example.cw.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.cw.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun SearchField(searchQuery: MutableState<TextFieldValue>, onValueChange: (input: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 6.dp, end = 6.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            modifier = Modifier.padding(start = 16.dp),
            tint = Color.Gray
        )

        BasicTextField(
            value = searchQuery.value.text,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontFamily = FontFamily.Default,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .fillMaxWidth()
                ) {
                    if (searchQuery.value.text.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.search_here),
                            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
