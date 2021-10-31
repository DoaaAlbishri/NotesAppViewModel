package com.example.notesappviewmodel.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesappviewmodel.dataBase.Note
import com.example.notesappviewmodel.dataBase.NoteDatabase
import com.example.notesappviewmodel.dataBase.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (applicationContext : Application): AndroidViewModel(applicationContext)  {
    val noteDao = NoteDatabase.getInstance(applicationContext).NoteDao()
    private val repository: NoteRepository = NoteRepository(noteDao)
    private val notes: LiveData<List<Note>> = repository.getNotes
    //notes = NoteDatabase.getInstance(applicationContext).NoteDao().getNote()
    //val applicationContext = applicationContext

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }

    fun addNote(noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            //NoteDatabase.getInstance(applicationContext).NoteDao().insertNote(Note(0,noteText))
            val n = Note(0, noteText)
            repository.addNote(n)
            println("added")
        }
    }

    fun updateNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            val n = Note(noteID, noteText)
            repository.updateNote(n)
            //NoteDatabase.getInstance(applicationContext).NoteDao().updateNote(n)
        }
        }

    fun deleteNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            val del = Note(noteID, noteText)
            repository.deleteNote(del)
            //NoteDatabase.getInstance(applicationContext).NoteDao().deleteNote(del)
        }
    }
}