package com.example.notedemoapp.usecase

import com.example.notedemoapp.data.repository.NoteRepository

class UpdateNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(timestamp: Long?, oldTitle: String, newTitle: String, content: String) {
        repository.updateNote(timestamp, oldTitle, newTitle, content)
    }
}