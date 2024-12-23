package com.example.cw.screens.base.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(onMenuIconTapped: () -> Unit, onCartIconTapped: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.GreenLife),
                color = lightGreen,
                style = TextStyle(fontWeight = FontWeight.Black, fontSize = 30.sp)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onMenuIconTapped()
            }) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = "Menu",
                    tint = icon
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onCartIconTapped()
            }) {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                    tint = icon
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}