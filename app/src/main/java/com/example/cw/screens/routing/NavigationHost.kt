package com.example.cw.screens.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.screens.home.main.widgets.BottomNavItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cw.screens.home.cart.CartFactory
import com.example.cw.screens.home.favorite.FavoriteFactory
import com.example.cw.screens.home.main.HomeFactory
import com.example.cw.screens.ordersHistory.OrdersHistoryFactory
import com.example.cw.screens.shippingAddresses.ShippingAddressesFactory


@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeFactory().build() }
        composable(BottomNavItem.Favorite.route) { FavoriteFactory().build() }
        composable(addressesRoute) { ShippingAddressesFactory().build() }
        composable(cartRoute) { CartFactory().build() }
        composable(ordersHistoryRoute) { OrdersHistoryFactory().build() }

    }
}






