package com.example.notedemoapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
        }
    }
}