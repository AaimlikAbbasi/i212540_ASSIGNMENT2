package com.example.assignment2
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.assignment2.R.layout.activity_main)

        val text="mentorme"
        val mspanningString= SpannableString(text)
        val myellow = ForegroundColorSpan(Color.parseColor("#FFD700"))

        mspanningString.setSpan(myellow,6,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        findViewById<TextView>(R.id.main).text = mspanningString

        val secondActivityBtn=findViewById<TextView>(com.example.assignment2.R.id.mentor2)

        secondActivityBtn.setOnClickListener {
            // Code to execute when the button is clicked
            val intent = Intent(this,  MainActivity2::class.java)
            startActivity(intent)
        }


        val user=findViewById<TextView>(com.example.assignment2.R.id.user)

      user.setOnClickListener {
            // Code to execute when the button is clicked
            val intent = Intent(this,  MainActivity21::class.java)
            startActivity(intent)
        }

    }


}