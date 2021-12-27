package com.example.notedemoapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.ui.screens.addnote.AddNoteScreen
import com.example.notedemoapp.ui.screens.details.DetailsScreen
import com.example.notedemoapp.ui.screens.editnote.EditNoteScreen
import com.example.notedemoapp.ui.screens.notes.NotesScreen
import com.example.notedemoapp.ui.screens.notes.components.DialogDeleteNote
import com.example.notedemoapp.util.Screen

@ExperimentalFoundationApi
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
                    navController.navigate(Screen.DialogDeleteNote.route)
                },
                onTap = {
                    note = it
                    navController.navigate(Screen.Details.route)
                }
            )
        }
        composable(Screen.AddNote.route) {
            AddNoteScreen(
                navController = navController
            )
        }
        composable(Screen.DialogDeleteNote.route) {
            DialogDeleteNote(
                navController = navController,
                note = note
            )
        }
        composable(Screen.Details.route) {
            DetailsScreen(
                navController = navController,
                note = note
            )
        }
        composable(Screen.EditNote.route + "/{title}/{content}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) {
            val title = remember {
                it.arguments?.getString("title")
            }
            val content = remember {
                it.arguments?.getString("content")
            }
            EditNoteScreen(
                title = title,
                content = content,
                navController = navController
            )
        }
    }
}