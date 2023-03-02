package com.example.notebook2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.Room.NoteDatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(private val service: NoteDatabaseService) : ViewModel() {

    fun insert(notesModel: NotesModel){
        viewModelScope.launch {
            service.getNotesDao().insertNote(notesModel)
        }
    }
}