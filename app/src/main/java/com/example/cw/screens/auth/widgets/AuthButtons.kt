package com.example.cw.screens.auth.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.mainText

@Composable
fun AuthButtons(
    onGoogleAuthPressed: () -> Unit,
    onContinueAsGuestTapped: () -> Unit,
){
    Box(
        modifier = Modifier.padding(horizontal = 70.dp, vertical = 10.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleSignInButton {
                onGoogleAuthPressed()
            }
            Text(
                text = "or",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = mainText.copy(alpha = 0.5f),
                    fontSize = 14.sp
                )
            )
            TextButton(onClick = { onContinueAsGuestTapped() }) {
                Text(
                    text = "Continue as guest",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = mainText.copy(alpha = 0.5f),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}