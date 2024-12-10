package com.example.cw.screens.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

class BaseFactory {

    @Composable
    fun Build(navHostController: NavHostController) {
        BaseScreen(navController = navHostController)
    }
}