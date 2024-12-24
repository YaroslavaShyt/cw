package com.example.cw.screens.base.home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.data.plants.Plant
import com.example.cw.screens.base.home.widgets.Banner
import com.example.cw.screens.base.home.widgets.CategoriesRow
import com.example.cw.screens.base.home.widgets.PlantItem
import com.example.cw.screens.base.home.widgets.SearchField
import com.example.cw.screens.base.widgets.NothingFoundPlaceholder
import com.example.cw.ui.theme.Salmon
import com.example.cw.ui.theme.olive

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val plantsFilteredState = viewModel.plantsFiltered.collectAsState()
    val likedPlants = viewModel.favoriteViewModel.likedPlants.collectAsState()
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val categoriesState = viewModel.categories.collectAsState()
    val selectedCategoryState = viewModel.selectedCategory.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()
    val scrollState = rememberLazyGridState()
    val bannerVisible = remember { mutableStateOf(true) }
    val focusManager = androidx.compose.ui.platform.LocalFocusManager.current
    val isTextFieldFocused = remember { mutableStateOf(false) }

    LaunchedEffect(remember { derivedStateOf { scrollState.firstVisibleItemIndex } }) {
        if (bannerVisible.value != (scrollState.firstVisibleItemIndex == 0)) {
            bannerVisible.value = scrollState.firstVisibleItemIndex == 0
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }) {
        if (isTextFieldFocused.value) {
            bannerVisible.value = false
        }

        if (loadingState.value) {
            CircularProgressIndicator(
                color = olive,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        } else if (errorState.value != null && errorState.value!!.isNotEmpty()) {
            errorState.value?.let {
                Text(text = stringResource(id = R.string.error))
            }
        } else {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                BuildBanner(isVisible = bannerVisible.value)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
                BuildSearchField(
                    onFocusChanged = {
                        isTextFieldFocused.value = it.isFocused
                        bannerVisible.value = !it.isFocused
                    },
                    searchQuery = searchQuery,
                    onValueChange = {
                        searchQuery.value = TextFieldValue(text = it)
                        viewModel.onSearchInputted(searchQuery.value.text)
                    },
                )
                CategoriesRow(categories = categoriesState.value,
                    selectedCategory = selectedCategoryState.value,
                    onTap = { viewModel.onCategorySelected(it) })
                BuildBody(
                    plantsFilteredState = plantsFilteredState,
                    searchQuery = searchQuery,
                    likedPlants = likedPlants,
                    scrollState = scrollState,
                    onPlantTapped = {
                        viewModel.onPlantTapped(it)
                    },
                    onLikeTapped = { viewModel.favoriteViewModel.onLikeTapped(it) },
                )
            }
        }
    }
}

@Composable
private fun BuildBanner(isVisible: Boolean) {
    AnimatedVisibility(visible = isVisible,
        enter = slideInVertically { fullHeight -> -fullHeight } + fadeIn(),
        exit = slideOutVertically { fullHeight -> -fullHeight } + fadeOut()) {
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
}

@Composable
private fun BuildSearchField(
    onFocusChanged: (FocusState) -> Unit,
    searchQuery: MutableState<TextFieldValue>,
    onValueChange: (String) -> Unit
) {
    Box(modifier = Modifier.onFocusChanged { focusState ->
        onFocusChanged(focusState)
    }) {
        SearchField(searchQuery = searchQuery, onValueChange = { query ->
            onValueChange(query)
        })
    }
}

@Composable
private fun BuildBody(
    plantsFilteredState: State<List<Plant>>,
    searchQuery: MutableState<TextFieldValue>,
    likedPlants: State<List<Plant>>,
    onPlantTapped: (String) -> Unit,
    onLikeTapped: (Plant) -> Unit,
    scrollState: LazyGridState,
) {
    if (plantsFilteredState.value.isEmpty() && searchQuery.value.text.isNotEmpty()) {
        NothingFoundPlaceholder()
    } else {
        val plantsToDisplay = remember(plantsFilteredState.value) { plantsFilteredState.value }
        LazyVerticalGrid(
            state = scrollState, columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
        ) {
            items(plantsToDisplay) { plant ->
                Box(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(onTap = { onPlantTapped(plant.id) })
                    },
                ) {
                    PlantItem(
                        plant,
                        isLiked = likedPlants.value.contains(plant),
                        onLikeTapped = { onLikeTapped(plant) }
                    )
                }
            }
        }

    }
}




