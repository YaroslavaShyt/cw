package com.example.cw.screens.base.home.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cw.ui.theme.black
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.mainWhite

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem("home", Icons.Rounded.Home, "Home")
    data object Favorite : BottomNavItem("favorite", Icons.Rounded.Favorite, "Favorite")
}

class BottomNavBarFactory(
    private val navHostController: NavHostController,
    private val isAuthorized: Boolean,
    private val onNotAuthorized: () -> Unit
) {

    @Composable
    fun Build() {
        BottomNavigationBar(
            navController = navHostController, isAuthorized = isAuthorized,
            onNotAuthorized = onNotAuthorized
        )
    }
}

@Composable
private fun BottomNavigationBar(
    navController: NavController, isAuthorized: Boolean,
    onNotAuthorized: () -> Unit
) {
    BottomNavigation(
        backgroundColor = mainWhite
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOf(BottomNavItem.Home, BottomNavItem.Favorite).forEach { item ->
            val isSelected: Boolean = currentRoute == item.route
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    if (item.route == "favorite" && !isAuthorized) {
                        onNotAuthorized()
                    } else {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        tint = if (isSelected) black else icon
                    )
                },
            )
        }
    }
}


