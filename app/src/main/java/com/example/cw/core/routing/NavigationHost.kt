package com.example.cw.core.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.cw.screens.base.home.widgets.BottomNavItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cw.screens.base.cart.CartFactory
import com.example.cw.screens.base.favorite.FavoriteFactory
import com.example.cw.screens.base.home.HomeFactory
import com.example.cw.screens.base.drawer.ordersHistory.OrdersHistoryFactory
import com.example.cw.screens.base.drawer.shippingAddresses.ShippingAddressesFactory
import com.example.cw.screens.base.plantDetails.PlantDetailsFactory


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
            PlantDetailsFactory(navHostController = navController).build(plantId = id)
        }
    }
}






