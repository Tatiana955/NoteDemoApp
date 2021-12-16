package com.example.notedemoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notedemoapp.R
import com.example.notedemoapp.ui.theme.NoteDemoAppTheme

@Composable
fun AppTopBar(
    title: @Composable RowScope.() -> Unit
) {
    Column {
        TopAppBar(
            elevation = 0.dp,
            contentColor = Color.White,
            title = { Row { title() } },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_note_24),
                    contentDescription = stringResource(R.string.note),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        )
    }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    NoteDemoAppTheme() {
        Surface {
            AppTopBar(title = { Text("AppTopBar") })
        }
    }
}

@Preview
@Composable
private fun AppTopBarPreviewDark() {
    NoteDemoAppTheme(darkTheme = true) {
        Surface {
            AppTopBar(title = { Text("AppTopBar") })
        }
    }
}