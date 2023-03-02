package com.example.notebook2.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notebook2.Model.NotesModel

@Database(entities = arrayOf(NotesModel::class), version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao
}