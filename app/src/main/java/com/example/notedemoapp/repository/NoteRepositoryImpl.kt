package com.example.notedemoapp.repository

import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository
import com.orm.SugarRecord.*

class NoteRepositoryImpl: NoteRepository {

    override suspend fun getNotes(): List<Note?> {
        return listAll(Note::class.java)
    }

    override suspend fun getNoteById(timestamp: Long): Note {
        return findById(Note::class.java, timestamp)
    }

    override suspend fun insertNote(note: Note) {
        note.save()
    }

    override suspend fun updateNote(timestamp: Long?, oldTitle: String, newTitle: String, content: String) {
        val notes: List<Note> = findWithQuery(
            Note::class.java,
            "Select * from Note where title = ?",
            oldTitle
        )
        notes.map {
            it.timestamp = timestamp
            it.title = newTitle
            it.content = content
            it.save()
        }
    }

    override suspend fun deleteNote(note: Note) {
        note.delete()
    }
}