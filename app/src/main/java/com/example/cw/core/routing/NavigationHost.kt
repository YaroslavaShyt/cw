package com.example.cw.core.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cw.screens.auth.AuthFactory
import com.example.cw.screens.base.BaseFactory
import com.example.cw.screens.base.cart.CartFactory
import com.example.cw.screens.base.drawer.ordersHistory.OrdersHistoryFactory
import com.example.cw.screens.base.drawer.shippingAddresses.ShippingAddressesFactory
import com.example.cw.screens.base.favorite.FavoriteFactory
import com.example.cw.screens.base.home.HomeFactory
import com.example.cw.screens.base.home.widgets.BottomNavItem
import com.example.cw.screens.base.plantDetails.PlantDetailsFactory


class NavigationAppFactory(private val navHostController: NavHostController) {

    @Composable
    fun Build() {
        NavigationApp(navController = navHostController)
    }
}

@Composable
private fun NavigationApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route)
        { HomeFactory(navController).Build() }

        composable(BottomNavItem.Favorite.route)
        { FavoriteFactory().Build() }

        composable(addressesRoute)
        { ShippingAddressesFactory().Build() }

        composable(cartRoute)
        { CartFactory().Build() }

        composable(ordersHistoryRoute)
        { OrdersHistoryFactory().Build() }

        composable(
            route = plantDetailsRoute,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PlantDetailsFactory(navController).Build(plantId = id)
        }
    }
}






