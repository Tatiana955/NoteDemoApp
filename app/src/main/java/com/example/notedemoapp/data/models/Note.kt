package com.example.notedemoapp.data.models

import com.orm.SugarRecord

data class Note(
    val title: String? = null,
    val content: String? = null,
    val timestamp: Long? = null,
    val color: Int? = null
): SugarRecord<Note?>()