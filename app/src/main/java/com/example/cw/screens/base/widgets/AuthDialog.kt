package com.example.cw.screens.base.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.cw.R
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.olive


@Composable
fun AuthDialog(
    onAuthorize: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(
                text = stringResource(id = R.string.authorization),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.to_proceede),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 18.sp)
            )
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = olive
                ),
                onClick = { onAuthorize() }) {
                Text(
                    stringResource(id = R.string.authorize),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = mainWhite,
                        fontSize = 18.sp
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = mainText,
                        fontSize = 18.sp
                    )
                )
            }
        },
        containerColor = mainWhite,
    )
}