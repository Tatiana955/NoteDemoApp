package com.example.notedemoapp.usecase

import com.example.notedemoapp.data.models.InvalidNoteException
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title?.isBlank() == true) {
            throw InvalidNoteException("Note title cannot be empty.")
        }
        if(note.content?.isBlank() == true) {
            throw InvalidNoteException("The content of the note cannot be empty.")
        }
        repository.insertNote(note)
    }
}