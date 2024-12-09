package com.example.cw.screens.base.drawer

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.core.widgets.NetworkImage
import com.example.cw.ui.theme.icon
import com.example.cw.ui.theme.lightGray
import com.example.cw.ui.theme.mainWhite
import com.example.cw.ui.theme.olive

@Composable
fun DrawerContent(
    userName: String, photo: String,
    onAddressClick: () -> Unit = {}
) {
    var isChecked by remember { mutableStateOf(false) }

    Column {
        UserNameAndImageRow(userName, photo)
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            HorizontalDivider()
            DrawerItem(
                title = "Theme",
                widget = {
                    MainSwitch(
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it })
                })
            HorizontalDivider()
            DrawerItem(title = "Orders history")
            HorizontalDivider()
            DrawerItem(title = "Language", widget = { })
            HorizontalDivider()
            DrawerItem(
                title = "My shipping address",
                onItemClick = onAddressClick,
            )
            HorizontalDivider()
            DrawerItem(title = "Logout")
            HorizontalDivider()
        }

    }


}

@Composable
private fun MainSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.padding(start = 20.dp),
        colors = SwitchDefaults.colors(
            checkedThumbColor = mainWhite,
            checkedTrackColor = olive,
            disabledUncheckedThumbColor = lightGray,
            disabledUncheckedTrackColor = mainWhite,
            disabledUncheckedBorderColor = icon,
        )
    )
}

@Composable
private fun UserNameAndImageRow(userName: String, photo: String) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(start = 20.dp, bottom = 20.dp)
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
                    .padding(end = 10.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    userName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
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
            .fillMaxWidth()
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
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                modifier = Modifier.padding(end = 20.dp)
            )
            widget?.let { it() }
        }
    }

}