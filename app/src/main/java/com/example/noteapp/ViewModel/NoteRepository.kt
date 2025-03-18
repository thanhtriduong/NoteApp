package com.example.noteapp.ViewModel

import com.example.noteapp.Database.NoteDao
import com.example.noteapp.Model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    fun getNoteById(id: Int): Flow<Note?> {
        return noteDao.getNoteById(id)
    }

}