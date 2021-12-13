package com.example.notedemoapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notedemoapp.ui.screens.addnote.AddNoteScreen
import com.example.notedemoapp.ui.screens.notes.NotesScreen
import com.example.notedemoapp.util.Screen

@ExperimentalFoundationApi
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route,
        modifier = modifier
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(
                navController = navController
            )
        }
        composable(Screen.AddNote.route) {
            AddNoteScreen(
                navController = navController
            )
        }
    }
}