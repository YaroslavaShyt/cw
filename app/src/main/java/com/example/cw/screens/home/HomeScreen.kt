package com.example.cw.screens.home

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.data.plants.Plant
import com.example.cw.screens.widgets.NetworkImage
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGreen
import com.example.cw.ui.theme.mainCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val plantsState = viewModel.plants.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.GreenLife),
                        color = lightGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {

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
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (loadingState.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                errorState.value?.let {
                    Text(text = "Error: $it")

                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(plantsState.value) { plant ->
                        PlantItem(plant)
                    }
                }


            }
        }
    )
}

@Composable
fun PlantItem(plant: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp),
        colors = CardColors(
            containerColor = mainCard,
            disabledContainerColor = mainCard,
            contentColor = mainCard,
            disabledContentColor = mainCard,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                NetworkImage(url = plant.image)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(text = plant.name, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = plant.price, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    CwTheme {
        HomeScreen(viewModel = HomeViewModel())
    }
}
