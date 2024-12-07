package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cw.domain.di.appModule
import com.example.cw.domain.services.IUserService
import com.example.cw.screens.home.main.widgets.BottomNavigationBar
import com.example.cw.screens.home.widgets.DrawerContent
import com.example.cw.screens.routing.NavigationApp
import com.example.cw.screens.routing.addressesRoute
import com.example.cw.screens.routing.cartRoute
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGreen
import com.example.cw.ui.theme.mainTypography
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin


class MainActivity : ComponentActivity(), KoinComponent {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            MaterialTheme(
                typography = mainTypography,
            ) {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()
                ModalDrawer(
                    drawerShape = RoundedCornerShape(20.dp),
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        DrawerContent(
                            onAddressClick = {
                                coroutineScope.launch { drawerState.close() }
                                navController.navigate(route = addressesRoute)
                            }
                        )
                    },
                    content = {
                        Scaffold(modifier = Modifier.fillMaxSize(),
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = {
                                        Text(
                                            stringResource(id = R.string.GreenLife),
                                            color = lightGreen,
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontWeight = FontWeight.W600
                                            )
                                        )
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            coroutineScope.launch {
                                                if (drawerState.isOpen) {
                                                    drawerState.close()
                                                } else {
                                                    drawerState.open()
                                                }
                                            }
                                        }) {
                                            Icon(
                                                Icons.Outlined.Menu,
                                                contentDescription = "Menu",
                                                tint = icon
                                            )
                                        }
                                    },
                                    actions = {
                                        IconButton(onClick = {
                                            navController.navigate(cartRoute)
                                        }) {
                                            Icon(
                                                Icons.Outlined.ShoppingCart,
                                                contentDescription = "Cart",
                                                tint = icon
                                            )
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            bottomBar = { BottomNavigationBar(navController = navController) }
                        ) { innerPadding ->
                            Column(
                                modifier = Modifier.padding(
                                    innerPadding
                                )
                            ) {
                                NavigationApp(navController = navController)
                            }
                        }
                    }
                )
            }
        }
    }
}