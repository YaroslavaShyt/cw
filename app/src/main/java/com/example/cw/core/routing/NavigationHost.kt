package com.example.cw.core.routing

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cw.screens.base.cart.CartFactory
import com.example.cw.screens.base.drawer.ordersHistory.OrdersHistoryFactory
import com.example.cw.screens.base.drawer.shippingAddresses.ShippingAddressesFactory
import com.example.cw.screens.base.favorite.FavoriteFactory
import com.example.cw.screens.base.home.HomeFactory
import com.example.cw.screens.base.home.widgets.BottomNavItem


class NavigationAppFactory(
    private val navHostController: NavHostController,
    private val onAuth: () -> Unit,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Build() {
        NavigationApp(navController = navHostController, onAuth)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NavigationApp(navController: NavHostController, onAuth: () -> Unit) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route)
        { HomeFactory(navController, onAuth = onAuth).Build() }

        composable(BottomNavItem.Favorite.route)
        { FavoriteFactory().Build() }

        composable(addressesRoute)
        { ShippingAddressesFactory().Build() }

        composable(cartRoute)
        { CartFactory().Build() }

        composable(ordersHistoryRoute)
        { OrdersHistoryFactory().Build() }
    }
}






