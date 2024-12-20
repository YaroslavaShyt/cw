package com.example.cw.screens.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cw.core.routing.NavigationApp
import com.example.cw.core.routing.addressesRoute
import com.example.cw.core.routing.cartRoute
import com.example.cw.screens.MainViewModel
import com.example.cw.screens.base.widgets.MainTopBar
import com.example.cw.screens.base.home.widgets.BottomNavigationBar
import com.example.cw.screens.base.drawer.DrawerContent
import kotlinx.coroutines.launch


class BaseActivity(navController: NavHostController) : ComponentActivity() {
    val _navController: NavHostController = navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            BaseFactory().Build(context = context, navHostController = _navController)
        }
    }

}

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val user = mainViewModel.user.collectAsState()
    val userName = viewModel.userName.collectAsState()
    val userPhoto = viewModel.userPhoto.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val language = viewModel.currentLanguage.collectAsState()

    ModalDrawer(
        drawerShape = RoundedCornerShape(20.dp),
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            if (userName.value != null && userPhoto.value != null) {
                DrawerContent(
                    currentLan = language.value,
                    userName = userName.value!!,
                    photo = userPhoto.value!!,
                    onAddressClick = {
                        coroutineScope.launch { drawerState.close() }
                        navController.navigate(route = addressesRoute)
                    },
                    onLanguageChanged = {
                        viewModel.changeLanguage(
                            context = context,
                            language = it
                        )
                    },
                    onLogoutTapped = {
                        coroutineScope.launch { drawerState.close() }
                        mainViewModel.onLogout()
                        viewModel.cleanData()
                    }
                )
            }
        }) {
        Scaffold(
            topBar = {
                MainTopBar(onMenuIconTapped = {
                    if (userName.value != null && userPhoto.value != null) {
                        coroutineScope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    }
                }, onCartIconTapped = {
                    navController.navigate(cartRoute)
                })
            },
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                NavigationApp(navController = navController)
            }
        }
    }

}

