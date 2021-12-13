package com.example.notedemoapp.util

sealed class Screen(var route: String, var icon: Int?, var title: String) {
    object Notes: Screen("notes", null, "NotesScreen")
    object AddNote: Screen("add_note", null, "AddNoteScreen")
}

