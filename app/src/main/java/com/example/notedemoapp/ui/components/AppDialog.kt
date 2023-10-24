package com.example.notedemoapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    text: String = "",
    confirmButtonText: String = "",
    dismissButtonText: String = "",
    onDismiss: () -> Unit = {},
    onNegativeClick: () -> Unit = {},
    onPositiveClick: () -> Unit = {}
) {
    AlertDialog(
        backgroundColor = Color.White,
        contentColor = MaterialTheme.colors.onBackground,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(8.dp)
            )
        },
        text = {
            Text(
                text = text,
                modifier = modifier
                    .padding(8.dp, 0.dp)
            )
        },
        confirmButton = {
            TextButton(
                onClick = onPositiveClick
            ) {
                Text(
                    text = confirmButtonText
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onNegativeClick
            ) {
                Text(
                    text = dismissButtonText
                )
            }
        }
    )
}