package com.example.cw.screens.home.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown() {
    var expanded by remember { mutableStateOf(false) } // Стан для відкриття/закриття меню
    var selectedLanguage by remember { mutableStateOf("en") } // Початковий вибір мови

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            TextField(
                value = selectedLanguage,
                onValueChange = { },
                readOnly = true,
                label = null,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .menuAnchor(),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.Transparent)
            ) {
                DropdownMenuItem(
                    text = { Text("English", fontSize = 16.sp, color = Color.Black) },
                    onClick = {
                        selectedLanguage = "en"
                        expanded = false
                    })
                DropdownMenuItem(
                    text = { Text("Ukrainian", fontSize = 16.sp, color = Color.Black) },
                    onClick = {
                        selectedLanguage = "uk"
                        expanded = false
                    })
            }
        }


    }

}
