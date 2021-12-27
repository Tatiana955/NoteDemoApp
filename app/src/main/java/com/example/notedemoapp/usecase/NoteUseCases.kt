package com.example.notedemoapp.usecase

data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val insertNote: AddNoteUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val updateNoteUseCase: UpdateNoteUseCase
)