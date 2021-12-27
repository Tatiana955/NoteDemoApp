package com.example.notedemoapp.util

sealed class Screen(var route: String, var icon: Int?, var title: String) {
    object Notes: Screen("notes", null, "NotesScreen")
    object AddNote: Screen("add_note", null, "AddNoteScreen")
    object DialogDeleteNote: Screen("delete_note", null, "DialogDeleteNote")
    object Details: Screen("details", null, "DetailsScreen")
    object EditNote: Screen("edit_note", null, "EditNoteScreen")
}

