package com.example.cw.screens.base.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cw.R

@Composable
fun Banner() {
    val locale = getCurrentLocale()
    var resource = R.drawable.banner
    if (locale != null) {
        resource = if (locale.language == "uk") R.drawable.banner_uk else R.drawable.banner
    }
    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .fillMaxSize(),
            painter = painterResource(id = resource), contentDescription = null
        )
    }
}

@Composable
fun getCurrentLocale(): java.util.Locale? {
    val context = LocalContext.current
    val configuration = context.resources.configuration
    return configuration.locales[0]
}
