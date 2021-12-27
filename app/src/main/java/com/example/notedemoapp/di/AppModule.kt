package com.example.notedemoapp.di

import androidx.lifecycle.ViewModel
import com.example.notedemoapp.ui.screens.notes.NoteViewModel
import com.example.notedemoapp.data.repository.NoteRepository
import com.example.notedemoapp.repository.NoteRepositoryImpl
import com.example.notedemoapp.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideViewModel(
        noteUseCases: NoteUseCases
    ): ViewModel {
        return NoteViewModel(noteUseCases)
    }

    @Singleton
    @Provides
    fun provideNoteRepository(
    ): NoteRepository {
        return NoteRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            insertNote = AddNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            updateNoteUseCase = UpdateNoteUseCase(repository)
        )
    }
}