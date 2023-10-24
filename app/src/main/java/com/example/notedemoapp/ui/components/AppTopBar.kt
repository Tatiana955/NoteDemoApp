package com.example.notedemoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notedemoapp.R

@Composable
fun AppTopBar(
    title: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        contentColor = Color.White,
        title = { Row { title() } },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_note_24),
                contentDescription = stringResource(R.string.note),
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    )
}