package com.example.evernote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.evernote.ExtendableFab.Note

class FreeTrial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_trial)

        setOnclickListeners()

    }

    private fun setOnclickListeners() {
        val trailBtn = findViewById<Button>(R.id.start_with_free_trial_Btn)

        trailBtn.setOnClickListener {

            val trailIntent = Intent(this, Note::class.java)

            startActivity(trailIntent)
        }

    }
}