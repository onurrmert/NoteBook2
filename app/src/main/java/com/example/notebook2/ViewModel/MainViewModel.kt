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
class MainViewModel @Inject constructor(private val service: NoteDatabaseService) : ViewModel() {

    val notesList = MutableLiveData<List<NotesModel>>()

    fun getAllNote(){
        viewModelScope.launch {
            notesList.value = service.getNotesDao().getAllNotes()
        }
    }
}