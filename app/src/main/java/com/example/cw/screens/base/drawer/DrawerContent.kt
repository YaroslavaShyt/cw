package com.example.cw.screens.base.drawer

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGray
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.olive
import com.example.cw.R
import com.example.cw.data.handlers.LocalizationHandler
import com.example.cw.ui.theme.commonGray
import com.example.cw.ui.theme.mainText
import com.example.cw.ui.theme.neatGreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerContent(
    userName: String, photo: String,
    onAddressClick: () -> Unit = {},
    onLogoutTapped: () -> Unit = {},
    currentLan: String,
    onLanguageChanged: (String) -> Unit,
) {
    var isChecked by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("uk", "en")


    Column {
        UserNameAndImageRow(userName, photo)
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            HorizontalDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.width(210.dp)) {
                    DrawerItem(
                        title = "Theme",
                    )
                }
                MainSwitch(
                    isChecked = isChecked,
                    onCheckedChange = { isChecked = it })
            }

            HorizontalDivider()
            DrawerItem(title = stringResource(id = R.string.ordersHistory))
            HorizontalDivider()

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        expanded = !expanded
                    }
                }
            ) {
                TextField(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures {
                            expanded = !expanded
                        }
                    },
                    value = stringResource(id = R.string.language),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        cursorColor = Color.White,
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    ),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures {
                                    expanded = !expanded
                                }
                            },
                        ) {
                            Text(
                                text = currentLan,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.pointerInput(Unit) {
                                    detectTapGestures {
                                        expanded = !expanded
                                    }
                                },
                            )
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        }

                    }

                )
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .offset(0.dp, (50.0).dp),
                    horizontalAlignment = Alignment.End
                ) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = !expanded
                        },
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            languages.forEach { language ->
                                DropdownMenuItem(onClick = {
                                    onLanguageChanged(language)
                                    expanded = false
                                }) {
                                    Text(
                                        text = language,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp
                                        ),
                                    )
                                }
                            }
                        }

                    }
                }
            }
            HorizontalDivider()
            DrawerItem(
                title = "My shipping address",
                onItemClick = onAddressClick,
            )
            HorizontalDivider()
            DrawerItem(title = "Logout", onItemClick = onLogoutTapped)
            HorizontalDivider()
        }

    }
}


@Composable
private fun MainSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = mainWhite,
            checkedTrackColor = neatGreen,
            uncheckedThumbColor = mainWhite,
            uncheckedTrackColor = mainText,
            uncheckedBorderColor = mainText
        ),
    )
}

@Composable
private fun UserNameAndImageRow(userName: String, photo: String) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .padding(start = 20.dp, bottom = 30.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            NetworkImage(
                url = photo, modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            )

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    userName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            }
        }
    }
}

@Composable
private fun DrawerItem(
    title: String,
    widget: @Composable (() -> Unit)? = null,
    onItemClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 20.dp)
                .height(100.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onItemClick() }
                    )
                }
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                modifier = Modifier.padding(end = 20.dp)
            )
            widget?.let { it() }
        }
    }

}

