package com.example.notedemoapp.data.models

import com.orm.SugarRecord

data class Note(
    var title: String? = null,
    var content: String? = null,
    var timestamp: Long? = null,
    val color: Int? = null
): SugarRecord<Note?>()