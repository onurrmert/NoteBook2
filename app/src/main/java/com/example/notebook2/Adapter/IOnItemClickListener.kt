package com.example.notebook2.Adapter

import com.example.notebook2.Model.NotesModel

interface IOnItemClickListener {
    fun itemClick(notesModel: NotesModel)
}