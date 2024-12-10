package com.example.cw.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.screens.auth.widgets.GoogleSignInButton
import com.example.cw.ui.theme.DarkGrey
import com.example.cw.ui.theme.Salmon

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
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
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Plant Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .align(Alignment.TopCenter)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp)
                        )
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Shop anywhere and",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGrey,
                            modifier = Modifier.padding(top = 35.dp)
                        )
                        Text(
                            text = "everywhere with",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGrey,
                            modifier = Modifier.padding(bottom = 25.dp)
                        )
                        Text(
                            text = "GreenLife",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Box(
                            modifier = Modifier.padding(horizontal = 70.dp, vertical = 20.dp)
                        ) {
                            GoogleSignInButton {
                                viewModel.onSignInButtonPressed()
                            }
                        }
                    }
                }
            }
        }

    }
}