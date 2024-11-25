package com.example.cw.screens.home.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.cw.ui.theme.mainWhite

@Composable
fun Drawer(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentScreen = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawer(
        // Drawer state indicates whether
        // the drawer is open or closed.
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            //drawerContent accepts a composable to represent
            // the view/layout that will be displayed
            // when the drawer is open.
//            DrawerContentComponent(
//                // We pass a state composable that represents the
//                // current screen that's selected
//                // and what action to take when the drawer is closed.
//                currentScreen = currentScreen,
//                closeDrawer = { coroutineScope.launch { drawerState.close() } }
//            )
        },
        content = {
            // bodyContent takes a composable to
            // represent the view/layout to display on the
            // screen. We select the appropriate screen
            // based on the value stored in currentScreen.
//            BodyContentComponent(
//                currentScreen = currentScreen.value,
//                openDrawer = {
//                    coroutineScope.launch { drawerState.open() }
//                }
//            )
        }
    )
}