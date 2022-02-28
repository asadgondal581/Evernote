package com.example.evernote.ExtendableFab

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.example.evernote.Mvvm.roomdb.Note
import com.example.evernote.Mvvm.roomdb.NoteViewModal
import com.example.evernote.R
import com.example.evernote.databinding.ActivityTextNoteBinding
import java.text.SimpleDateFormat
import java.util.*


class TextNoteActivity : AppCompatActivity() {


    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: AppCompatImageView
    private lateinit var binding: ActivityTextNoteBinding
    lateinit var viewModal: NoteViewModal
    var noteID = -1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)


        val noteType = intent.getStringExtra("noteType")


        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
        }



        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)



        saveBtn.setOnClickListener {
             saveData()
            Toast.makeText(this, "Gree Tick Clicked!", Toast.LENGTH_SHORT).show()

        }

        val templateBtn = findViewById<Button>(R.id.templateBtn)
        templateBtn.setOnClickListener {
            // sendData()
            Toast.makeText(this, "Template Button Clicked!", Toast.LENGTH_SHORT).show()

        }



        val signature = findViewById<AppCompatImageView>(R.id.img_signature)

        //val intent = getIntent().getStringExtra("BitmapImage");
        //val bitmaptn = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
        //val bitmap = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
        //signature.setImageBitmap(bitmaptn)

    }



    private fun saveData() {

        val noteTitle = noteTitleEdt.text.toString()
        val noteDescription = noteEdt.text.toString()
        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                updatedNote.id = noteID
                viewModal.updateNote(updatedNote)
                Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
            }
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())

                viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
            }
        }

        startActivity(Intent(applicationContext, com.example.evernote.ExtendableFab.Note::class.java))
        this.finish()

    }



}