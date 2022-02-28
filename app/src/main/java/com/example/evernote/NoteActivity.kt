package com.example.evernote

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.evernote.ExtendableFab.TextNoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class NoteActivity : AppCompatActivity() {

    lateinit var mDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        mDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val fabBtn = findViewById<FloatingActionButton>(R.id.fab)
            fabBtn.setOnClickListener {

                val dialog = Dialog(this)
                dialog.setContentView(R.layout.login_dialog)

                val dialogBtn = dialog.findViewById<Button>(R.id.dialogTextNoteBtn)
                dialogBtn.setOnClickListener {
                    val noteIntent = Intent(this@NoteActivity, TextNoteActivity::class.java)
                    startActivity(noteIntent)
                }


                dialog.show()

        }



        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_allNotes ->

                    Toast.makeText(applicationContext,"Clicked AllNotes",Toast.LENGTH_SHORT).show()
                R.id.nav_noteBooks ->
                    Toast.makeText(applicationContext,"Clicked NoteBooks",Toast.LENGTH_SHORT).show()
                R.id.nav_sharedWithMe ->
                    Toast.makeText(applicationContext,"Clicked Shared with Me",Toast.LENGTH_SHORT).show()
                R.id.nav_collectPhotos ->
                    Toast.makeText(applicationContext,"Clicked Collect Photos",Toast.LENGTH_SHORT).show()
                R.id.nav_workChat ->
                    Toast.makeText(applicationContext,"Clicked Work Chat",Toast.LENGTH_SHORT).show()
                R.id.nav_trash ->
                    Toast.makeText(applicationContext,"Clicked Trash",Toast.LENGTH_SHORT).show()
                R.id.nav_darkTheme ->  Toast.makeText(applicationContext,"Clicked Dark Theme",Toast.LENGTH_SHORT).show()
                R.id.nav_settings ->
                    Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.nav_exploreEvernote ->
                    Toast.makeText(applicationContext,"Clicked Explore Evernote",Toast.LENGTH_SHORT).show()

            }

            true


        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if ( mDrawerToggle.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
