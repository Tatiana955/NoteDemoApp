package com.example.notedemoapp.util

import androidx.compose.ui.graphics.vector.ImageVector

class FabItem(
    val icon: ImageVector,
    val label: String,
    val onFabItemClicked: () -> Unit
)