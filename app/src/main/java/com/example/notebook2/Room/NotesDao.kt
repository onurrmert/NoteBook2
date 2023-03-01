package com.example.notebook2.Room

import androidx.room.*
import com.example.notebook2.Model.NotesModel

@Dao
interface NotesDao {

    @Query("SELECT * FROM notesTable ORDER BY history DESC")
    suspend fun getAllNotes() : List<NotesModel>

    @Query("SELECT * FROM notesTable WHERE id = :id")
    suspend fun getOneNote(id : Int) : NotesModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(notesModel: NotesModel)

    @Update(entity = NotesModel::class)
    suspend fun updateNote(notesModel: NotesModel)

    @Query("DELETE FROM notesTable WHERE id = :id")
    suspend fun deleteNote(id : Int)
}