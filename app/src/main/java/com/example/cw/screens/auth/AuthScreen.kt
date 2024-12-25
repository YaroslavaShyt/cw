package com.example.cw.screens.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cw.screens.auth.widgets.AuthButtons
import com.example.cw.screens.auth.widgets.AuthImage
import com.example.cw.screens.auth.widgets.AuthText
import com.example.cw.screens.auth.widgets.CustomShapeContainer
import com.example.cw.screens.base.BaseFactory
import com.example.cw.ui.theme.Salmon

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthScreen(viewModel: AuthViewModel, navHostController: NavHostController) {
    val isContinueAsGuest = viewModel.isContinueAsGuestState.collectAsState()

    if (isContinueAsGuest.value) {
        BaseFactory(navHostController) {
            viewModel.isContinueAsGuest.value = false
        }.Build()
    } else {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Salmon)
                ) {
                    AuthImage()
                    CustomShapeContainer()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(354.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        AuthText()
                        AuthButtons(
                            onGoogleAuthPressed = { viewModel.onSignInButtonPressed() },
                            onContinueAsGuestTapped = {
                                viewModel.isContinueAsGuest.value = true
                            }
                        )
                    }
                }
            }
        }
    }

}

