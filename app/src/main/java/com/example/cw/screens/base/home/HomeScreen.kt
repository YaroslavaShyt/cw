package com.example.cw.screens.base.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.screens.base.home.widgets.CategoriesRow
import com.example.cw.screens.base.home.widgets.PlantItem
import com.example.cw.screens.base.home.widgets.SearchField


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val plantsFilteredState = viewModel.plantsFiltered.collectAsState()
    val likedPlants = viewModel.favoriteViewModel.likedPlants.collectAsState()
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val categoriesState = viewModel.categories.collectAsState()
    val selectedCategoryState = viewModel.selectedCategory.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (loadingState.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        errorState.value?.let {
            Text(text = "Error: $it")
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            SearchField(
                searchQuery = searchQuery,
                onValueChange = { query ->
                    searchQuery.value = TextFieldValue(text = query)
                    viewModel.onSearchInputted(searchQuery.value.text)
                }
            )

            CategoriesRow(
                categories = categoriesState.value,
                selectedCategory = selectedCategoryState.value,
                onTap = { viewModel.onCategorySelected(it) }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(plantsFilteredState.value) { plant ->
                    Box(modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { _ ->
                                viewModel.onPlantTapped(plant.id)
                            }
                        )
                    }) {
                        PlantItem(plant,
                            isLiked = likedPlants.value.contains(plant),
                            onLikeTapped = { viewModel.favoriteViewModel.onLikeTapped(plant) }
                        )
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {

}
