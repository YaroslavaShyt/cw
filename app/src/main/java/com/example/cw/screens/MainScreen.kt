package com.example.cw.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.cw.R
import com.example.cw.screens.auth.AuthFactory
import com.example.cw.screens.base.BaseFactory


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navHostController: NavHostController, viewModel: MainViewModel) {
    val user = viewModel.user.collectAsState()
    val launcher = viewModel.getAuthFlow()
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current
    if (user.value == null) {
        return AuthFactory(navHostController).Build {
            viewModel.getSignInClient(token, context)?.let { launcher.launch(it.signInIntent) }
        }
    } else {
        viewModel.onAuthSuccess(user.value!!)
        return BaseFactory(navHostController) { viewModel.onAuth() }.Build()
    }
}

