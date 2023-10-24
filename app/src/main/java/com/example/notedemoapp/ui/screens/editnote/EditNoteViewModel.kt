package com.example.notedemoapp.ui.screens.editnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedemoapp.data.models.InvalidNoteException
import com.example.notedemoapp.util.AddEditNoteEvent
import com.example.notedemoapp.util.UiEvent
import com.example.notedemoapp.ui.screens.editnote.util.NoteEditTextFieldState
import com.example.notedemoapp.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    var oldTitle: String = ""
    private val _noteTitle = mutableStateOf(
        NoteEditTextFieldState()
    )
    val noteTitle: State<NoteEditTextFieldState> = _noteTitle

    var oldContent: String = ""
    private val _noteContent = mutableStateOf(
        NoteEditTextFieldState()
    )
    val noteContent: State<NoteEditTextFieldState> = _noteContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    oldText = oldTitle,
                    newText = event.value
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    oldText = oldTitle,
                    newText = noteTitle.value.newText,
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    oldText = oldContent,
                    newText = event.value
                )
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    oldText = oldContent,
                    newText = noteContent.value.newText
                )
            }
            // AddEditNoteEvent.SaveNote
            else -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.updateNoteUseCase(
                            timestamp = System.currentTimeMillis(),
                            oldTitle = oldTitle,
                            newTitle = if (noteTitle.value.newText.isEmpty()) {
                                oldTitle
                            } else {
                                noteTitle.value.newText
                            },
                            content = if (noteContent.value.newText.isEmpty()) {
                                oldContent
                            } else {
                                noteContent.value.newText
                            }
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Failed to save note"
                            )
                        )
                    }
                }
            }
        }
    }
}