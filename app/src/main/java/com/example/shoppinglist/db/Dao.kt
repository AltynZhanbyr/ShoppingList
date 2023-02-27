package com.example.shoppinglist.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.entities.NoteItem
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    suspend fun insertNote(note:NoteItem)

    @Delete
    suspend fun deleteNote(note: NoteItem)

    @Update
    suspend fun updateNote(note: NoteItem)

    @Query("SELECT * FROM note_list")
    fun getAllNotes(): Flow<List<NoteItem>>
}