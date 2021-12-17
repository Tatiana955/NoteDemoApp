package com.example.notedemoapp.ui.screens.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    var notesList = mutableStateOf<List<Note?>>(listOf())

    fun getNotes() {
        viewModelScope.launch {
            val data = noteUseCases.getNotes()
            notesList.value = data
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
        }
    }
}