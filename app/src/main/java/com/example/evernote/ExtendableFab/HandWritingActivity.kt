package com.example.evernote.ExtendableFab
import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.example.evernote.R
import com.example.evernote.databinding.ActivityHandWritingBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kyanogen.signatureview.SignatureView


class HandWritingActivity : AppCompatActivity() {
   // lateinit var binding : ActivityHandWritingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_writing)

        val backArrow = findViewById<ImageView>(R.id.backArrow_sign_save)
        val bottom_Arrow_Btn = findViewById<FloatingActionButton>(R.id.bottom_hand_fab)

        val  btn_sign_submit = findViewById<AppCompatButton>(R.id.btn_sign_submit)
        val   btn_sign_clear = findViewById<AppCompatButton>(R.id.btn_sign_clear)
        val   img_signature = findViewById<AppCompatImageView>(R.id.img_signature)
        val signature_view = findViewById<SignatureView>(R.id.signature_view)



        btn_sign_submit.setOnClickListener {
            if (signature_view.isBitmapEmpty) {
                Toast.makeText(this, "please make any signature", Toast.LENGTH_SHORT).show();
            } else {
                val bitmap = signature_view.signatureBitmap
                img_signature.setImageBitmap(bitmap)
            }
        }

        btn_sign_clear.setOnClickListener {
            signature_view.clearCanvas()
        }
       /* backArrow.setOnClickListener {
           if (signature_view.isBitmapEmpty) {
                Toast.makeText(this, "please make any signature", Toast.LENGTH_SHORT).show();
            } else {
                val bitmap = signature_view.signatureBitmap
               val intentHW = Intent(this, TextNoteActivity::class.java)
               intentHW.putExtra("BitmapImage", bitmap)
               Toast.makeText(this, "back clicked", Toast.LENGTH_SHORT).show()
               startActivity(intentHW)
               finish()
            }

        }*/
        backArrow.setOnClickListener {
            val intentHW = Intent(this, TextNoteActivity::class.java)
            startActivity(intentHW)
            finish()
        }


        bottom_Arrow_Btn.setOnClickListener {

            val idFabBTn = findViewById<FloatingActionButton>(R.id.up_hand_fab)
            idFabBTn.isVisible = true

        }

    }

}