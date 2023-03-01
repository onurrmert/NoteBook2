package com.example.notebook2.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notebook2.Model.NotesModel

@Database(entities = arrayOf(NotesModel::class), version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object{
        @Volatile
        private var INSTANCE : NotesDatabase? = null

        fun getDatabase(context: Context) : NotesDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, NotesDatabase::class.java, "notesDatabase").build()
                INSTANCE = instance
                instance
            }
        }
    }
}