package com.example.notedemoapp.usecase

import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(): List<Note?> {
        return repository.getNotes()
    }
}