package com.example.notedemoapp.usecase

import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Note {
        return repository.getNoteById(id)
    }
}