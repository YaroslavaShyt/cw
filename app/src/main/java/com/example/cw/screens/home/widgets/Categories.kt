package com.example.cw.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.commonGray
import com.example.cw.ui.theme.lightGray
import com.example.cw.ui.theme.lightGreen
import com.example.cw.ui.theme.mainWhite

@Composable
fun CategoriesRow(categories: List<String>, selectedCategory: String, onTap: (category:String)->Unit) {
    Row(
        modifier = Modifier
            .scrollable(
                orientation = Orientation.Horizontal,
                state = rememberScrollState()
            )
            .padding(bottom = 20.dp)
    ) {
        categories.forEach { category ->
            Box(
                modifier = Modifier.padding(
                    start = if (categories.indexOf(category) == 0) 10.dp else 0.dp,
                    end = 10.dp
                ).pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { offset ->
                            onTap(category)
                        },
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = if(category == selectedCategory) lightGreen else lightGray,
                            shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = if(category == selectedCategory) mainWhite else commonGray
                        )
                    )
                }
            }
        }
    }
}