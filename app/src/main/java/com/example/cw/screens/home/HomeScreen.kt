package com.example.cw.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.Plant
import com.example.cw.ui.theme.CwTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val plantsState = viewModel.plants.collectAsState()
    val loadingState = viewModel.loading.collectAsState()
    val errorState = viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Plants List") })
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

                LazyColumn(modifier = Modifier.fillMaxSize()) {
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
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${plant.name}")
            Text(text = "Description: ${plant.description}")
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
