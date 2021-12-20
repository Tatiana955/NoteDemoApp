package com.example.notedemoapp.repository

import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.data.repository.NoteRepository
import com.orm.SugarRecord.findById
import com.orm.SugarRecord.listAll

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

    override suspend fun deleteNote(note: Note) {
        note.delete()
    }
}