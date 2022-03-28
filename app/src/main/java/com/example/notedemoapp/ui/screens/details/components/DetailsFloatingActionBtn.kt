package com.example.notedemoapp.ui.screens.details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.notedemoapp.R
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.ui.components.AppMultiFloatingActionButton
import com.example.notedemoapp.ui.screens.details.DetailsViewModel
import com.example.notedemoapp.util.FabItem
import com.example.notedemoapp.util.Screen

@Composable
fun DetailsFloatingActionBtn(
    navController: NavController,
    note: Note?,
    viewModel: DetailsViewModel
) {
    val fabItems = listOf(
        FabItem(
            Icons.Filled.Edit,
            stringResource(R.string.edit)
        ) {
          navController.navigate(Screen.EditNote.route +
                  "/${note?.title}/${note?.content}/${note?.color}"
          )
        },
        FabItem(
            Icons.Filled.Delete,
            stringResource(R.string.del)
        ) {
            note?.let {
                viewModel.deleteNote(it)
            }
            navController.popBackStack()
        }
    )
    AppMultiFloatingActionButton(
        fabIcon = Icons.Filled.Add,
        items = fabItems
    )
}