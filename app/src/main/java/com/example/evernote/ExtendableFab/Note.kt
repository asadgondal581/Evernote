package com.example.evernote.ExtendableFab

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evernote.Adapter.NoteClickDeleteInterface
import com.example.evernote.Adapter.NoteClickInterface
import com.example.evernote.Adapter.NoteRVAdapter
import com.example.evernote.Mvvm.roomdb.Note
import com.example.evernote.Mvvm.roomdb.NoteViewModal
import com.example.evernote.R
import com.example.evernote.RightNav.SelectNote
import com.example.evernote.RightNav.SettingActivityOptionMenu
import com.example.evernote.databinding.ActivityNote2Binding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*

class Note : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {


    var isOpen = true
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNote2Binding
   lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNote2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarNote.toolbar)


        notesRV = findViewById(R.id.notesRV)
        notesRV.layoutManager = LinearLayoutManager(this)
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)
        viewModal.allNotes.observe(this, androidx.lifecycle.Observer { list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })




        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRclockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAnticlockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)



        val addFabBtn = findViewById<FloatingActionButton>(R.id.add_fab)
        val textNoteFabBtn = findViewById<FloatingActionButton>(R.id.textNote_fab)
        val cameraFabBtn = findViewById<FloatingActionButton>(R.id.camera_fab)
        val handWritingFabBtn = findViewById<FloatingActionButton>(R.id.handWriting_fab)
        val attachmentFabBtn = findViewById<FloatingActionButton>(R.id.attachment_fab)
        val audioFabBtn = findViewById<FloatingActionButton>(R.id.audio_fab)
        val reminderFabBtn = findViewById<FloatingActionButton>(R.id.reminder_fab)

        val textNoteBtn = findViewById<Button>(R.id.textNoteExBtn)
        val cameraBtn = findViewById<Button>(R.id.cameraBtn)
        val handwritingBtn = findViewById<Button>(R.id.handWritingBtn)
        val attachmentBtn = findViewById<Button>(R.id.attachmentBtn)
        val audioBtn = findViewById<Button>(R.id.audioBtn)
        val reminderBtn = findViewById<Button>(R.id.reminderBtn)


        textNoteFabBtn.isClickable
        cameraFabBtn.isClickable
        handWritingFabBtn.isClickable
        attachmentFabBtn.isClickable
        audioFabBtn.isClickable
        reminderFabBtn.isClickable


        textNoteBtn.setOnClickListener {
            textNote()
        }
        cameraBtn.setOnClickListener {  }
        handwritingBtn.setOnClickListener {
            handWriting()
        }
        attachmentBtn.setOnClickListener {


        }
        audioBtn.setOnClickListener {

        }
        reminderBtn.setOnClickListener {

        }


        textNoteFabBtn.setOnClickListener {
            textNote()
        }
        cameraFabBtn.setOnClickListener {

        }
        handWritingFabBtn.setOnClickListener {
            handWriting()

        }
        attachmentFabBtn.setOnClickListener {

        }
        audioFabBtn.setOnClickListener {

        }
        reminderFabBtn.setOnClickListener {

        }
        addFabBtn.setOnClickListener {

        }

        addFabBtn.setOnClickListener {

            if (!isOpen) {

                textNoteFabBtn.startAnimation(fabClose)
                cameraFabBtn.startAnimation(fabClose)
                handWritingFabBtn.startAnimation(fabClose)
                attachmentFabBtn.startAnimation(fabClose)
                audioFabBtn.startAnimation(fabClose)
                reminderFabBtn.startAnimation(fabClose)
                textNoteBtn.startAnimation(fabClose)
                cameraBtn.startAnimation(fabClose)
                handwritingBtn.startAnimation(fabClose)
                attachmentBtn.startAnimation(fabClose)
                audioBtn.startAnimation(fabClose)
                reminderBtn.startAnimation(fabClose)
                addFabBtn.startAnimation(fabRclockWise)

                isOpen = true

            }
            else

            {
                Log.i("TAG", "inside of click listener:else statement")
                textNoteFabBtn.startAnimation(fabOpen)
                cameraFabBtn.startAnimation(fabOpen)
                handWritingFabBtn.startAnimation(fabOpen)
                attachmentFabBtn.startAnimation(fabOpen)
                audioFabBtn.startAnimation(fabOpen)
                reminderFabBtn.startAnimation(fabOpen)
                textNoteBtn.startAnimation(fabOpen)
                cameraBtn.startAnimation(fabOpen)
                handwritingBtn.startAnimation(fabOpen)
                attachmentBtn.startAnimation(fabOpen)
                audioBtn.startAnimation(fabOpen)
                reminderBtn.startAnimation(fabOpen)
                addFabBtn.startAnimation(fabRAnticlockWise)
                isOpen = false

            }

        }





        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_note)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.note, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_note)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_selectNotes -> {
                Toast.makeText(this, "This is alll notes Clicked", Toast.LENGTH_SHORT).show()
                val mIntent = Intent(this, SelectNote::class.java)
                startActivity(mIntent)
                return true
            }

            R.id.action_AddtoHomeSccreen -> {
                val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Enter the shortcut title")


                val input = EditText(this)
                input.setHint("Notelist shortcut")
                input.inputType = InputType.TYPE_CLASS_TEXT
                builder.setView(input)


                builder.setPositiveButton("SAVE", DialogInterface.OnClickListener {
                        dialog, which ->
                   // var m_Text = input.text.toString()
                })
                builder.setNegativeButton(
                    "CANCEL",
                    DialogInterface.OnClickListener {
                            dialog, which -> dialog.cancel() })

                builder.show()
                return true
            }
            R.id.action_SortBy -> {

                val mDialogView = LayoutInflater.from(this).inflate(R.layout.sortby, null)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Sort by")
                // val  mAlertDialog =
                mBuilder.show()
                return true
            }
            R.id.action_Viewoptions -> {
                val builder = AlertDialog.Builder(this)

                val colorsArray = arrayOf("Show Images", "Show Tags", "Show Text", "Show Note Size")
                val checkedColorsArray = booleanArrayOf(
                    true,
                    false, // Orange
                    false, // Green
                    true, // Yellow checked
                    false, // White
                    false  //Purple
                )
                val colorsList = Arrays.asList(*colorsArray)
                builder.setTitle("View Options")
                builder.setMultiChoiceItems(
                    colorsArray,
                    checkedColorsArray
                ) { dialog, which, isChecked ->

                    checkedColorsArray[which] = isChecked

                    val currentItem = colorsList[which]

                    Toast.makeText(applicationContext, currentItem + " " + isChecked, Toast.LENGTH_SHORT).show()
                }

                builder.setPositiveButton("OK") { dialog, which ->

                    val mSlctdTxtTv = findViewById<TextView>(R.id.textView)
                    mSlctdTxtTv.text = "Your preferred colors..... \n"
                    for (i in checkedColorsArray.indices) {
                        val checked = checkedColorsArray[i]
                        if (checked) {
                            mSlctdTxtTv.text = mSlctdTxtTv.text.toString() + colorsList[i] + "\n"
                        }
                    }
                }

                builder.setNeutralButton("Cancel") { dialog, which ->

                }
                val dialog = builder.create()

                dialog.show()
                return true
            }
            R.id.action_Sync -> {

                return true
            }
            R.id.action_settings -> {
                val i = Intent(this@Note, SettingActivityOptionMenu::class.java)
                startActivity(i)
                return true
            }
            R.id.action_selectNotes -> {

                return true
            }
            R.id.action_selectPayment -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }





fun textNote(){
    val noteIntent = Intent(this@Note, TextNoteActivity::class.java)
    startActivity(noteIntent)
    Toast.makeText(this, "text note clicked", Toast.LENGTH_SHORT).show()
    this.finish()
}

    fun camera(){

    }
    fun handWriting(){
        val noteIntent2 = Intent(this@Note, HandWritingActivity::class.java)
        startActivity(noteIntent2)
        Toast.makeText(this, "HandWriting clicked", Toast.LENGTH_SHORT).show()
        this.finish()
    }
    fun attachment(){




    }
    fun audio(){

    }
    fun reminder(){

    }




    override fun onDeleteIconClick(note: Note) {

        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()


    }



    override fun onNoteClick(note: Note) {
        val intent = Intent(this@Note, TextNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }



}
