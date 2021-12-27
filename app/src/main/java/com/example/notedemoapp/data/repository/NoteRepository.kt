package com.example.notedemoapp.data.repository

import com.example.notedemoapp.data.models.Note

interface NoteRepository {

    suspend fun getNotes(): List<Note?>

    suspend fun getNoteById(timestamp: Long): Note

    suspend fun insertNote(note: Note)

    suspend fun updateNote(timestamp: Long?, oldTitle: String, newTitle: String, content: String)

    suspend fun deleteNote(note: Note)
}