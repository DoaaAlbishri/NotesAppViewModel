package com.example.notesappviewmodel

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var myRv: RecyclerView
    //var list = ArrayList<String>()
    lateinit var lv: List<Note>
    private val myViewModel by lazy {ViewModelProvider(this).get(MyViewModel::class.java)}
    lateinit var Rv : RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button)
        myRv = findViewById<RecyclerView>(R.id.recyclerView)

        lv = arrayListOf()
        NoteDatabase.getInstance(applicationContext)
        Rv = RecyclerViewAdapter(this)

        myViewModel.getNotes().observe(this, {
            notes -> Rv.update(notes)
        })

        myRv.adapter = Rv
        myRv.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            if (!editText.text.isEmpty()) {
                save()
            } else {
                Toast.makeText(this, "The text is empty", Toast.LENGTH_SHORT).show()
            }
            editText.text.clear()
        }
    }

    //fun show() {
    //    myViewModel.getNotes()
    //    myRv()
    //}

    fun save() {
        var noteText = editText.text.toString()
        myViewModel.addNote(noteText)
        Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT).show()
    }

    fun update(ID: Int) {
        //first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        input.hint = "Enter new note"
        // positive button text and action
        dialogBuilder.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
            val str = input.text.toString()
            if (str.isEmpty()) {
                Toast.makeText(applicationContext, "Fill all filed please!! ", Toast.LENGTH_SHORT).show()
            } else {
                 myViewModel.updateNote(ID,str)
                Toast.makeText(applicationContext, "data updated successfully! ", Toast.LENGTH_SHORT).show()
            }
        })
            // negative button text and action
            .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, id ->
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Update Note")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }

    fun delete(id: Int, s1: String) {
        myViewModel.deleteNote(id,s1)
        Toast.makeText(applicationContext, "data deleted successfully! ", Toast.LENGTH_SHORT).show()
    }
}