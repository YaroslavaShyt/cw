package com.example.cw.screens.base.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cw.screens.base.home.widgets.PlantItem

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel) {
    val likedPlants = viewModel.likedPlants.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()
    Scaffold { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            if (loadingState.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            errorState.value?.let {
                Text(text = "Error: $it")
            }

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Favorite")

                if (likedPlants.value.isEmpty()) {
                    Text(text = "Nothing was marked as favorite yet ")
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(likedPlants.value) { plant ->
                            PlantItem(
                                plant,
                                isLiked = true,
                                onLikeTapped = { viewModel.onLikeTapped(plant) }
                            )
                        }
                    }
                }


            }
        }

    }
}