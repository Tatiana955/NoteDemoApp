package com.example.notedemoapp.usecase

import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}