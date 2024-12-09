//package com.example.cw.screens.home
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.Menu
//import androidx.compose.material.icons.outlined.ShoppingCart
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import com.example.cw.R
//import com.example.cw.core.routing.NavigationApp
//import com.example.cw.core.routing.addressesRoute
//import com.example.cw.core.routing.cartRoute
//import com.example.cw.screens.home.main.widgets.BottomNavigationBar
//import com.example.cw.screens.home.widgets.DrawerContent
//import com.example.cw.ui.theme.icon
//import com.example.cw.ui.theme.lightGreen
//import kotlinx.coroutines.launch
//
//ModalDrawer(
//drawerShape = RoundedCornerShape(20.dp),
//drawerState = drawerState,
//gesturesEnabled = drawerState.isOpen,
//drawerContent = {
//    DrawerContent(
//        onAddressClick = {
//            coroutineScope.launch { drawerState.close() }
//            navController.navigate(route = addressesRoute)
//        }
//    )
//},
//content = {
//    Scaffold(modifier = Modifier.fillMaxSize(),
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        stringResource(id = R.string.GreenLife),
//                        color = lightGreen,
//                        style = MaterialTheme.typography.bodyLarge.copy(
//                            fontWeight = FontWeight.W600
//                        )
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        coroutineScope.launch {
//                            if (drawerState.isOpen) {
//                                drawerState.close()
//                            } else {
//                                drawerState.open()
//                            }
//                        }
//                    }) {
//                        Icon(
//                            Icons.Outlined.Menu,
//                            contentDescription = "Menu",
//                            tint = icon
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {
//                        navController.navigate(cartRoute)
//                    }) {
//                        Icon(
//                            Icons.Outlined.ShoppingCart,
//                            contentDescription = "Cart",
//                            tint = icon
//                        )
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            )
//        },
//        bottomBar = { BottomNavigationBar(navController = navController) }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier.padding(
//                innerPadding
//            )
//        ) {
//            NavigationApp(navController = navController)
//        }
//    }
//}
//)
//}}