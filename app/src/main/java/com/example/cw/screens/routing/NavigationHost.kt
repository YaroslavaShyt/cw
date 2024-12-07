package com.example.cw.screens.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cw.screens.home.main.widgets.BottomNavItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cw.screens.home.cart.CartScreen
import com.example.cw.screens.home.cart.CartViewModel
import com.example.cw.screens.home.favorite.FavoriteScreen
import com.example.cw.screens.home.favorite.FavoriteViewModel
import com.example.cw.screens.home.main.HomeScreen
import com.example.cw.screens.home.main.HomeViewModel
import com.example.cw.screens.shippingAddresses.ShippingAddressesScreen
import com.example.cw.screens.shippingAddresses.ShippingAddressesViewModel


@Composable
fun NavigationApp(navController: NavHostController) {
    val homeViewModel: HomeViewModel = viewModel()
    val favoriteViewModel: FavoriteViewModel = viewModel()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeFactory(
                homeViewModel,
                favoriteViewModel,
                navController
            )
        }
        composable(BottomNavItem.Favorite.route) {
            FavoriteFactory(
                favoriteViewModel,
                navController
            )
        }
        composable(addressesRoute) { AddressesFactory(navController = navController) }
        composable(cartRoute) { CartFactory(navController = navController) }

    }
}

@Composable
fun HomeFactory(
    homeViewModel: HomeViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    HomeScreen(
        viewModel = homeViewModel,
        favoriteViewModel = favoriteViewModel,
        navController = navController
    )
}

@Composable
fun FavoriteFactory(favoriteViewModel: FavoriteViewModel, navController: NavHostController) {
    FavoriteScreen(viewModel = favoriteViewModel)
}

@Composable
fun AddressesFactory(navController: NavHostController) {
    val addressesViewModel: ShippingAddressesViewModel = viewModel()
    ShippingAddressesScreen(viewModel = addressesViewModel)
}

@Composable
fun CartFactory(navController: NavHostController) {
    val cartViewModel : CartViewModel = viewModel()
    CartScreen(viewModel = cartViewModel)
}

