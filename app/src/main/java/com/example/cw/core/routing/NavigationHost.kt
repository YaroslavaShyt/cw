package com.example.cw.core.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.cw.screens.home.main.widgets.BottomNavItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cw.screens.home.cart.CartFactory
import com.example.cw.screens.home.favorite.FavoriteFactory
import com.example.cw.screens.home.main.HomeFactory
import com.example.cw.screens.drawer.ordersHistory.OrdersHistoryFactory
import com.example.cw.screens.drawer.shippingAddresses.ShippingAddressesFactory
import com.example.cw.screens.plantDetails.PlantDetailsFactory


@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeFactory(navHostController = navController).build() }
        composable(BottomNavItem.Favorite.route) { FavoriteFactory().build() }
        composable(addressesRoute) { ShippingAddressesFactory().build() }
        composable(cartRoute) { CartFactory().build() }
        composable(ordersHistoryRoute) { OrdersHistoryFactory().build() }
        composable(
            route = plantDetailsRoute,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PlantDetailsFactory().build(plantId = id)
        }
    }
}






