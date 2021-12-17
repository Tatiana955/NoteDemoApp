package com.example.notedemoapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notedemoapp.ui.theme.NoteDemoAppTheme

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

@Preview
@Composable
private fun AppDialogPreview() {
    NoteDemoAppTheme {
        Surface {
            AppDialog(
                title = "Title",
                text = "Text",
                confirmButtonText = "Btn",
                dismissButtonText = "Btn",
            )
        }
    }
}