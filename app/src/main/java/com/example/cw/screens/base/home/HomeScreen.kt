package com.example.cw.screens.base.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.screens.base.home.widgets.Banner
import com.example.cw.screens.base.home.widgets.CategoriesRow
import com.example.cw.screens.base.home.widgets.PlantItem
import com.example.cw.screens.base.home.widgets.SearchField
import com.example.cw.ui.theme.Salmon


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

    val scrollState = rememberLazyGridState()
    val bannerVisible = remember { mutableStateOf(true) }

    LaunchedEffect(remember { derivedStateOf { scrollState.firstVisibleItemIndex } }) {
        bannerVisible.value =
            scrollState.firstVisibleItemIndex == 0
    }

    val focusRequester = remember { FocusRequester() }
    val isTextFieldFocused = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().pointerInput(Unit){
            detectTapGestures {
                focusRequester.freeFocus()
            }
        }
    ) {
        if (isTextFieldFocused.value) {
            bannerVisible.value = false
        }

        if (loadingState.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        errorState.value?.let {
            Text(text = "Error: $it")
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            if (bannerVisible.value) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Salmon)
                ) {
                    Banner()
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 20.dp))

            Box(modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isTextFieldFocused.value = focusState.isFocused
                    bannerVisible.value =
                        !focusState.isFocused // При втраті фокусу банер з'являється
                }) {
                SearchField(
                    searchQuery = searchQuery,
                    onValueChange = { query ->
                        searchQuery.value = TextFieldValue(text = query)
                        viewModel.onSearchInputted(searchQuery.value.text)
                    },

                    )
            }


            CategoriesRow(
                categories = categoriesState.value,
                selectedCategory = selectedCategoryState.value,
                onTap = { viewModel.onCategorySelected(it) }
            )

            LazyVerticalGrid(
                state = scrollState,
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
                        PlantItem(
                            plant,
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
    // Placeholder for preview
}
