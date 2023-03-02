package com.example.notebook2.Adapter

import android.view.View
import com.example.notebook2.Model.NotesModel

interface IOnItemClickListener {
    fun itemClick(notesModel: NotesModel, view: View)
}