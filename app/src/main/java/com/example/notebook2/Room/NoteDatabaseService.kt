package com.example.notebook2.Room

import javax.inject.Inject

class NoteDatabaseService @Inject constructor(private val notesDao: NotesDao) {

    suspend fun getNotesDao() : NotesDao{
        return notesDao
    }
}