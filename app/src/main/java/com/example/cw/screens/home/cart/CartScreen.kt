package com.example.cw.screens.home.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.cw.R
import com.example.cw.screens.home.cart.widgets.CartPlantComponent

@Composable
fun CartScreen(viewModel: CartViewModel){
    val cartContent = viewModel.plants.collectAsState()
    
    Column {
        Row {
            Text(text = stringResource(id = R.string.cart))
            Text(text = stringResource(id = R.string.total) )

        }
        
        LazyColumn {
            items(cartContent.value){ plant -> 
                CartPlantComponent(plant = plant)
            }
        }
    }
}