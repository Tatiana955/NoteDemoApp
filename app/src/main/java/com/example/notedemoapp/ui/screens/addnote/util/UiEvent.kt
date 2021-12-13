package com.example.notedemoapp.ui.screens.addnote.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}