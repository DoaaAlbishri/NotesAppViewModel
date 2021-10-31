package com.example.notesappviewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (applicationContext : Application): AndroidViewModel(applicationContext)  {
    private val notes: LiveData<List<Note>>
    val applicationContext = applicationContext

    init {
        notes = NoteDatabase.getInstance(applicationContext).StudentDao().getNote()
    }

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }

    fun addNote(noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).StudentDao().insertNote(Note(0,noteText))
            println("added")
        }
    }

    fun updateNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
                val s = Note(noteID, noteText)
                    NoteDatabase.getInstance(applicationContext).StudentDao().updateNote(s)
                }
        }

    fun deleteNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            val del = Note(noteID, noteText)
            NoteDatabase.getInstance(applicationContext).StudentDao().deleteNote(del)
        }
    }
}