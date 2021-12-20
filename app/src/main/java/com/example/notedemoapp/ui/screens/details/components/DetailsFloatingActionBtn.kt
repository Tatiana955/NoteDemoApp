package com.example.notedemoapp.ui.screens.details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.notedemoapp.R
import com.example.notedemoapp.ui.components.AppMultiFloatingActionButton
import com.example.notedemoapp.util.FabItem

@Composable
fun DetailsFloatingActionBtn() {
    val fabItems = listOf(
        FabItem(
            Icons.Filled.Edit,
            stringResource(R.string.edit)
        ) {

        },
        FabItem(
            Icons.Filled.Delete,
            stringResource(R.string.del)
        ) {

        }
    )
    AppMultiFloatingActionButton(
        fabIcon = Icons.Filled.Add,
        items = fabItems
    )
}