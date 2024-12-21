package com.example.cw.screens.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cw.core.routing.NavigationAppFactory
import com.example.cw.screens.MainViewModel
import com.example.cw.screens.base.drawer.DrawerContent
import com.example.cw.screens.base.home.widgets.BottomNavBarFactory
import com.example.cw.screens.base.widgets.AuthDialog
import com.example.cw.screens.base.widgets.MainTopBar
import kotlinx.coroutines.launch


@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
) {
    val user = mainViewModel.user.collectAsState()
    val userName = viewModel.userName.collectAsState()
    val userPhoto = viewModel.userPhoto.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val language = viewModel.currentLanguage.collectAsState()
    val isAuthPopupShown = remember { mutableStateOf(false) }

    val isAuthorized = userName.value != null && userPhoto.value != null

    if (isAuthPopupShown.value) {
        AuthDialog(
            onAuthorize = { viewModel.onAuthButtonTapped() },
            onDismiss = { isAuthPopupShown.value = false }
        )
    }

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
                        viewModel.onAddressTapped()
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
                    if (isAuthorized) {
                        coroutineScope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    } else {
                        isAuthPopupShown.value = true
                    }
                }, onCartIconTapped = {
                    if (isAuthorized) {
                        viewModel.onCartTapped()

                    } else {
                        isAuthPopupShown.value = true
                    }
                })
            },
            bottomBar = {
                BottomNavBarFactory(
                    navHostController,
                    isAuthorized,
                    onNotAuthorized = { isAuthPopupShown.value = true },
                ).Build()
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                NavigationAppFactory(navHostController) { viewModel.onAuthButtonTapped() }.Build()
            }
        }
    }

}

