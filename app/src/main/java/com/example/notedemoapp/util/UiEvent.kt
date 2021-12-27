package com.example.notedemoapp.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}