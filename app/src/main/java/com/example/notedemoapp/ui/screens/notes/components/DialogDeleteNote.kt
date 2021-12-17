package com.example.notedemoapp.ui.screens.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notedemoapp.R
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.ui.components.AppDialog
import com.example.notedemoapp.ui.screens.notes.NoteViewModel

@Composable
fun DialogDeleteNote(
    note: Note?,
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    AppDialog(
        title = stringResource(R.string.delete),
        confirmButtonText = stringResource(R.string.yes),
        dismissButtonText = stringResource(R.string.no),
        onDismiss = {
            navController.popBackStack()
        },
        onNegativeClick = {
            navController.popBackStack()
        },
        onPositiveClick = {
            note?.let { note ->
                viewModel.deleteNote(note)
            }
            navController.popBackStack()
        }
    )
}