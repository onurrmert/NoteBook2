package com.example.notebook2.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo("notes")
    val notes: String,

    @ColumnInfo("history")
    val history: String
){
    constructor(title: String, notes: String, history: String) : this(0, title, notes, history)
}
