package com.example.cw.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.R
import com.example.cw.screens.home.widgets.CategoriesRow
import com.example.cw.screens.home.widgets.PlantItem
import com.example.cw.screens.home.widgets.SearchField
import com.example.cw.ui.theme.CwTheme
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val plantsState = viewModel.plants.collectAsState()
    val plantsFilteredState = viewModel.plantsFiltered.collectAsState()
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val categoriesState = viewModel.categories.collectAsState()
    val selectedCategoryState = viewModel.selectedCategory.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.GreenLife),
                        color = lightGreen,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
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
                            PlantItem(plant)
                        }
                    }
                }


            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    CwTheme {
        HomeScreen(viewModel = HomeViewModel())
    }
}
