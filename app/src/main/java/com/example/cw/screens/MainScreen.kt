package com.example.cw.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.cw.R
import com.example.cw.screens.auth.AuthFactory
import com.example.cw.screens.base.BaseFactory


@Composable
fun MainScreen(viewModel: MainViewModel, navHostController: NavHostController) {
    val user = viewModel.user.collectAsState()
    val launcher = viewModel.getAuthFlow()
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current
    if (user.value == null) {
        return AuthFactory().Build {
            viewModel.getSignInClient(token, context)?.let { launcher.launch(it.signInIntent) }
        }
    } else {
        viewModel.onAuthSuccess(user.value!!)
        return BaseFactory().Build(navHostController = navHostController, context = context)
    }
}

