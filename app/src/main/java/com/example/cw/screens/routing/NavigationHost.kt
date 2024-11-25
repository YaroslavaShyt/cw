package com.example.cw.screens.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cw.screens.home.main.widgets.BottomNavItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cw.screens.home.favorite.FavoriteScreen
import com.example.cw.screens.home.main.HomeScreen
import com.example.cw.screens.home.main.HomeViewModel


@Composable
fun NavigationApp(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route){
        composable(BottomNavItem.Home.route) {  HomeFactory(navController)}
        composable(BottomNavItem.Favorite.route) { FavoriteFactory(navController) }
    }
}

@Composable
fun HomeFactory(navController: NavHostController){
    val homeViewModel: HomeViewModel = viewModel()
    HomeScreen(viewModel = homeViewModel, navController = navController)
}

@Composable
fun FavoriteFactory(navController: NavHostController){
    val homeViewModel: HomeViewModel = viewModel()
   FavoriteScreen()
}

