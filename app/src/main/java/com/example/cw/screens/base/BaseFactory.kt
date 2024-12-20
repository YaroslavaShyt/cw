package com.example.cw.screens.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BaseFactory : KoinComponent {
    private val userService: IUserService by inject()
    private val authService: IAuthService by inject()


    @Composable
    fun Build(navHostController: NavHostController, context: Context) {
        BaseScreen(
            viewModel = BaseViewModel(
                userService = userService,
                authService = authService,
                context = context
            ),
            mainViewModel = MainViewModel(
                authService,
            ),
            navController = navHostController
        )
    }
}