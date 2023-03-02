package com.example.notebook2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.Room.NoteDatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val service: NoteDatabaseService) : ViewModel() {

    val noteModel = MutableLiveData<NotesModel>()

    fun getNote(id : Int){
        viewModelScope.launch {
            noteModel.value = service.getNotesDao().getOneNote(id)
        }
    }
}