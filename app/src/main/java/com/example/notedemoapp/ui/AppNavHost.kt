package com.example.notedemoapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.ui.screens.addnote.AddNoteScreen
import com.example.notedemoapp.ui.screens.notes.NotesScreen
import com.example.notedemoapp.ui.screens.notes.components.DialogDeleteNote
import com.example.notedemoapp.util.Screen

@ExperimentalFoundationApi
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var note: Note? = null
    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route,
        modifier = modifier
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(
                navController = navController,
                onLongClick = {
                    note = it
                    navController.navigate(Screen.Dialog.route)
                }
            )
        }
        composable(Screen.AddNote.route) {
            AddNoteScreen(
                navController = navController
            )
        }
        composable(Screen.Dialog.route) {
            DialogDeleteNote(
                navController = navController,
                note = note
            )
        }
    }
}